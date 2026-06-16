package com.tandem.community.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeCommunityRepository : CommunityRepository {

    private val likedIds = MutableStateFlow<Set<Int>>(emptySet())
    private val pages = mutableMapOf<Int, Result<List<CommunityMember>>>()

    val requestedPages = mutableListOf<Int>()

    fun setPage(page: Int, result: Result<List<CommunityMember>>) {
        pages[page] = result
    }

    override fun getCommunityMembers(): Flow<PagingData<CommunityMember>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { FakePagingSource() },
        ).flow

    override fun observeLikedIds(): Flow<Set<Int>> = likedIds

    override suspend fun setLiked(memberId: Int, liked: Boolean) {
        likedIds.update { current ->
            if (liked) current + memberId else current - memberId
        }
    }

    private inner class FakePagingSource : PagingSource<Int, CommunityMember>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommunityMember> {
            val page = params.key ?: FIRST_PAGE
            requestedPages += page
            val result = pages[page] ?: Result.success(emptyList())
            return result.fold(
                onSuccess = { members ->
                    LoadResult.Page(
                        data = members,
                        prevKey = if (page == FIRST_PAGE) null else page - 1,
                        nextKey = if (members.size < PAGE_SIZE) null else page + 1,
                    )
                },
                onFailure = { LoadResult.Error(it) },
            )
        }

        override fun getRefreshKey(state: PagingState<Int, CommunityMember>): Int? = null
    }

    companion object {
        const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
