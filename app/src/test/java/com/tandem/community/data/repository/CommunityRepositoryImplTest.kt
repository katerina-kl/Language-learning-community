package com.tandem.community.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.tandem.community.data.remote.CommunityApi
import com.tandem.community.util.FakeLikedMemberDao
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommunityRepositoryImplTest {

    private val api: CommunityApi = mockk()
    private val dao = FakeLikedMemberDao()

    @Test
    fun `setLiked persists and observeLikedIds reflects changes`() = runTest {
        val repository = CommunityRepositoryImpl(
            api = api,
            likedMemberDao = dao,
            ioDispatcher = Dispatchers.Unconfined,
        )

        repository.observeLikedIds().test {
            assertThat(awaitItem()).isEmpty()

            repository.setLiked(5, true)
            assertThat(awaitItem()).containsExactly(5)

            repository.setLiked(5, false)
            assertThat(awaitItem()).isEmpty()
        }
    }
}
