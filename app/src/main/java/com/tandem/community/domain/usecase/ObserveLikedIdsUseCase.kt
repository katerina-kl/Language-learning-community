package com.tandem.community.domain.usecase

import com.tandem.community.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Observes the ids of members the user has liked. */
class ObserveLikedIdsUseCase @Inject constructor(
    private val repository: CommunityRepository,
) {
    operator fun invoke(): Flow<Set<Int>> = repository.observeLikedIds()
}
