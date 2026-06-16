package com.tandem.community.presentation.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.presentation.community.components.CommunityCard
import com.tandem.community.presentation.community.components.CommunityDivider
import com.tandem.community.presentation.theme.HeaderBackground
import com.tandem.community.presentation.theme.HeaderTitle

@Composable
fun CommunityRoute(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel(),
) {
    val members = viewModel.members.collectAsLazyPagingItems()
    val likedIds by viewModel.likedIds.collectAsStateWithLifecycle()
    CommunityScreen(
        members = members,
        likedIds = likedIds,
        onLikeToggled = viewModel::onLikeToggled,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    members: LazyPagingItems<CommunityMember>,
    likedIds: Set<Int>,
    onLikeToggled: (CommunityMember) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = HeaderBackground,
        topBar = {
            Surface(
                shadowElevation = 4.dp,
                color = HeaderBackground,
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Community",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = HeaderTitle,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = HeaderBackground,
                        titleContentColor = HeaderTitle,
                        scrolledContainerColor = HeaderBackground,
                    ),
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when {
                members.loadState.refresh is LoadState.Loading && members.itemCount == 0 -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                members.loadState.refresh is LoadState.Error && members.itemCount == 0 -> {
                    val error = members.loadState.refresh as LoadState.Error
                    ErrorState(
                        message = error.error.message ?: "Something went wrong.",
                        onRetry = members::retry,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                else -> {
                    CommunityList(
                        members = members,
                        likedIds = likedIds,
                        onLikeToggled = onLikeToggled,
                    )
                }
            }
        }
    }
}

@Composable
private fun CommunityList(
    members: LazyPagingItems<CommunityMember>,
    likedIds: Set<Int>,
    onLikeToggled: (CommunityMember) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = members.itemCount,
            key = members.itemKey { it.id },
        ) { index ->
            val member = members[index]
            if (member != null) {
                CommunityCard(
                    member = member.copy(isLiked = member.id in likedIds),
                    onLikeToggled = onLikeToggled,
                )
                if (index < members.itemCount - 1) {
                    CommunityDivider()
                }
            }
        }

        when (val appendState = members.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    LoadMoreError(
                        onRetry = members::retry,
                    )
                }
            }

            else -> Unit
        }
    }
}

@Composable
private fun LoadMoreError(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Couldn't load more members.",
            style = MaterialTheme.typography.bodyMedium,
        )
        Button(onClick = onRetry, modifier = Modifier.padding(top = 8.dp)) {
            Text("Retry")
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Button(onClick = onRetry, modifier = Modifier.padding(top = 16.dp)) {
            Text("Try again")
        }
    }
}
