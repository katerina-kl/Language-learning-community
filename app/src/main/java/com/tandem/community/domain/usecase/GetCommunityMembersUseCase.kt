package com.tandem.community.domain.usecase

import androidx.paging.PagingData
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommunityMembersUseCase @Inject constructor(
    private val repository: CommunityRepository,
) {
    operator fun invoke(): Flow<PagingData<CommunityMember>> =
        repository.getCommunityMembers()
}
