package com.tandem.community.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tandem.community.data.local.LikedMemberDao
import com.tandem.community.data.local.LikedMemberEntity
import com.tandem.community.data.paging.CommunityPagingSource
import com.tandem.community.data.remote.CommunityApi
import com.tandem.community.di.IoDispatcher
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.domain.repository.CommunityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityRepositoryImpl @Inject constructor(
    private val api: CommunityApi,
    private val likedMemberDao: LikedMemberDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CommunityRepository {

    override fun getCommunityMembers(): Flow<PagingData<CommunityMember>> =
        Pager(
            config = PagingConfig(
                pageSize = CommunityPagingSource.PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { CommunityPagingSource(api) },
        ).flow

    override fun observeLikedIds(): Flow<Set<Int>> =
        likedMemberDao.observeLikedIds().map { it.toSet() }

    override suspend fun setLiked(memberId: Int, liked: Boolean) =
        withContext(ioDispatcher) {
            if (liked) {
                likedMemberDao.like(LikedMemberEntity(memberId))
            } else {
                likedMemberDao.unlike(memberId)
            }
        }
}
