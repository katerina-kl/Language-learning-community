package com.tandem.community.presentation.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.domain.usecase.GetCommunityMembersUseCase
import com.tandem.community.domain.usecase.ObserveLikedIdsUseCase
import com.tandem.community.domain.usecase.ToggleLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    getCommunityMembers: GetCommunityMembersUseCase,
    observeLikedIds: ObserveLikedIdsUseCase,
    private val toggleLike: ToggleLikeUseCase,
) : ViewModel() {

    val members: Flow<PagingData<CommunityMember>> =
        getCommunityMembers().cachedIn(viewModelScope)

    val likedIds: StateFlow<Set<Int>> =
        observeLikedIds().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptySet(),
        )

    fun onLikeToggled(member: CommunityMember) {
        viewModelScope.launch {
            toggleLike(member.id, member.isLiked)
        }
    }

    companion object {
        private const val STOP_TIMEOUT_MS = 5_000L
    }
}
