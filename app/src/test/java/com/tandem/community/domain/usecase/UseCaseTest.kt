package com.tandem.community.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.tandem.community.util.FakeCommunityRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UseCaseTest {

    private val repository = FakeCommunityRepository()

    @Test
    fun `GetCommunityMembersUseCase returns paging flow from repository`() = runTest {
        val useCase = GetCommunityMembersUseCase(repository)

        val flow = useCase()

        assertThat(flow).isNotNull()
    }

    @Test
    fun `ToggleLikeUseCase likes a member that is currently unliked`() = runTest {
        val useCase = ToggleLikeUseCase(repository)

        useCase(memberId = 10, currentlyLiked = false)

        repository.observeLikedIds().test {
            assertThat(awaitItem()).contains(10)
        }
    }

    @Test
    fun `ToggleLikeUseCase unlikes a member that is currently liked`() = runTest {
        repository.setLiked(10, true)
        val useCase = ToggleLikeUseCase(repository)

        useCase(memberId = 10, currentlyLiked = true)

        repository.observeLikedIds().test {
            assertThat(awaitItem()).doesNotContain(10)
        }
    }

    @Test
    fun `ObserveLikedIdsUseCase streams repository likes`() = runTest {
        val useCase = ObserveLikedIdsUseCase(repository)

        useCase().test {
            assertThat(awaitItem()).isEmpty()
            repository.setLiked(1, true)
            assertThat(awaitItem()).containsExactly(1)
        }
    }
}
