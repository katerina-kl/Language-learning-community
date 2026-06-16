package com.tandem.community.domain.repository

import androidx.paging.PagingData
import com.tandem.community.domain.model.CommunityMember
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getCommunityMembers(): Flow<PagingData<CommunityMember>>

    fun observeLikedIds(): Flow<Set<Int>>

    suspend fun setLiked(memberId: Int, liked: Boolean)
}
