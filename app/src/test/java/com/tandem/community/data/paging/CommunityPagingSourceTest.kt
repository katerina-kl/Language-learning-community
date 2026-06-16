package com.tandem.community.data.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.tandem.community.data.remote.CommunityApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CommunityPagingSourceTest {

    private lateinit var server: MockWebServer
    private lateinit var pagingSource: CommunityPagingSource

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(CommunityApi::class.java)
        pagingSource = CommunityPagingSource(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `loads first page and provides next key when page is full`() = runTest {
        server.enqueue(MockResponse().setBody(fullPageJson()))

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false),
        ) as PagingSource.LoadResult.Page

        assertThat(result.data).hasSize(20)
        assertThat(result.data.first().firstName).isEqualTo("Tobi")
        assertThat(result.data.first().isNew).isTrue()
        assertThat(result.prevKey).isNull()
        assertThat(result.nextKey).isEqualTo(2)
        assertThat(server.takeRequest().path).isEqualTo("/api/community_1.json")
    }

    @Test
    fun `loads specific page number`() = runTest {
        server.enqueue(MockResponse().setBody(shortPageJson()))

        val result = pagingSource.load(
            PagingSource.LoadParams.Append(key = 2, loadSize = 20, placeholdersEnabled = false),
        ) as PagingSource.LoadResult.Page

        assertThat(result.data).hasSize(2)
        assertThat(result.prevKey).isEqualTo(1)
        assertThat(result.nextKey).isNull()
        assertThat(server.takeRequest().path).isEqualTo("/api/community_2.json")
    }

    @Test
    fun `returns error when request fails`() = runTest {
        server.enqueue(MockResponse().setResponseCode(500))

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false),
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

    private fun fullPageJson(): String {
        val members = (1..20).joinToString(",") { id ->
            """
            {
              "id": $id,
              "topic": "Topic",
              "firstName": "${if (id == 1) "Tobi" else "Member$id"}",
              "pictureUrl": "https://example.com/p.png",
              "natives": ["de"],
              "learns": ["en"],
              "referenceCnt": ${if (id == 1) 0 else 5}
            }
            """.trimIndent()
        }
        return """{"response":[$members],"errorCode":null,"type":"success"}"""
    }

    private fun shortPageJson(): String = """
        {
          "response": [
            {
              "id": 21,
              "topic": "Topic",
              "firstName": "Last",
              "pictureUrl": "https://example.com/p.png",
              "natives": ["de"],
              "learns": ["en"],
              "referenceCnt": 1
            },
            {
              "id": 22,
              "topic": "Topic",
              "firstName": "Final",
              "pictureUrl": "https://example.com/p.png",
              "natives": ["de"],
              "learns": ["en"],
              "referenceCnt": 2
            }
          ],
          "errorCode": null,
          "type": "success"
        }
    """.trimIndent()
}
