package com.tandem.community.presentation.community

import com.google.common.truth.Truth.assertThat
import com.tandem.community.domain.usecase.GetCommunityMembersUseCase
import com.tandem.community.domain.usecase.ObserveLikedIdsUseCase
import com.tandem.community.domain.usecase.ToggleLikeUseCase
import com.tandem.community.util.FakeCommunityRepository
import com.tandem.community.util.MainDispatcherRule
import com.tandem.community.util.member
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommunityViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeCommunityRepository()

    private fun createViewModel() = CommunityViewModel(
        getCommunityMembers = GetCommunityMembersUseCase(repository),
        observeLikedIds = ObserveLikedIdsUseCase(repository),
        toggleLike = ToggleLikeUseCase(repository),
    )

    @Test
    fun `toggling like updates likedIds`() = runTest {
        val viewModel = createViewModel()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.likedIds.collect {}
        }
        advanceUntilIdle()

        assertThat(viewModel.likedIds.value).isEmpty()

        viewModel.onLikeToggled(member(id = 1))
        advanceUntilIdle()
        assertThat(viewModel.likedIds.value).contains(1)

        viewModel.onLikeToggled(member(id = 1, isLiked = true))
        advanceUntilIdle()
        assertThat(viewModel.likedIds.value).doesNotContain(1)
    }
}
