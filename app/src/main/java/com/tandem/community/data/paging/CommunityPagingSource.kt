package com.tandem.community.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tandem.community.data.mapper.toDomain
import com.tandem.community.data.remote.CommunityApi
import com.tandem.community.domain.model.CommunityMember

/**
 * Loads community members page by page from the remote API.
 *
 * Page keys are 1-based to match the endpoint (`community_1.json`, etc.).
 * The next page is requested only when the current one returns a full page.
 */
class CommunityPagingSource(
    private val api: CommunityApi,
) : PagingSource<Int, CommunityMember>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommunityMember> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val members = api.getCommunity(page).response.orEmpty().toDomain()
            LoadResult.Page(
                data = members,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (members.size < PAGE_SIZE) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommunityMember>): Int? =
        state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }

    companion object {
        const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
