package com.tandem.community.domain.usecase

import com.tandem.community.domain.repository.CommunityRepository
import javax.inject.Inject

/**
 * Toggles and persists the like state of a community member.
 */
class ToggleLikeUseCase @Inject constructor(
    private val repository: CommunityRepository,
) {
    suspend operator fun invoke(memberId: Int, currentlyLiked: Boolean) {
        repository.setLiked(memberId, !currentlyLiked)
    }
}
