package com.tandem.community.presentation.community.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tandem.community.domain.model.CommunityMember
import com.tandem.community.presentation.theme.LabelDark
import com.tandem.community.presentation.theme.LabelMuted
import com.tandem.community.presentation.theme.LanguageLearningCommunityTheme
import com.tandem.community.presentation.theme.LikedBlue
import com.tandem.community.presentation.theme.NewBadgeTeal
import com.tandem.community.presentation.theme.RowBackground

@Composable
fun CommunityCard(
    member: CommunityMember,
    onLikeToggled: (CommunityMember) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(RowBackground)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Avatar(pictureUrl = member.pictureUrl, name = member.firstName)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp),
        ) {
            HeaderRow(member = member)

            Text(
                text = member.topic,
                style = MaterialTheme.typography.bodyMedium,
                color = LabelMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 6.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LanguageGroup(label = "NATIVE", codes = member.natives)
                Spacer(modifier = Modifier.width(24.dp))
                LanguageGroup(label = "LEARNS", codes = member.learns)
                Spacer(modifier = Modifier.weight(1f))
                ThumbReaction(
                    isLiked = member.isLiked,
                    onClick = { onLikeToggled(member) },
                )
            }
        }
    }
}

@Composable
private fun HeaderRow(member: CommunityMember) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = member.firstName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f, fill = false),
        )
        Spacer(modifier = Modifier.width(8.dp))
        if (member.isNew) {
            NewBadge()
        } else {
            Text(
                text = member.referenceCount.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = LabelMuted,
            )
        }
    }
}

@Composable
private fun Avatar(pictureUrl: String, name: String) {
    AsyncImage(
        model = pictureUrl,
        contentDescription = "$name profile picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(76.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
    )
}

@Composable
private fun ThumbReaction(
    isLiked: Boolean,
    onClick: () -> Unit,
) {
    val scale by animateFloatAsState(
        targetValue = if (isLiked) 1.1f else 1f,
        label = "thumbScale",
    )
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = if (isLiked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
            contentDescription = if (isLiked) "Liked" else "Not liked",
            tint = if (isLiked) LikedBlue else LabelMuted,
            modifier = Modifier
                .size(26.dp)
                .scale(scale)
                .semantics { contentDescription = if (isLiked) "Liked" else "Not liked" },
        )
    }
}

@Composable
private fun NewBadge(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(NewBadgeTeal)
            .padding(horizontal = 10.dp, vertical = 3.dp),
    ) {
        Text(
            text = "NEW",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun LanguageGroup(label: String, codes: List<String>) {
    if (codes.isEmpty()) return
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            color = LabelDark,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = codes.joinToString(" ") { it.uppercase() },
            style = MaterialTheme.typography.labelSmall,
            color = LabelMuted,
        )
    }
}

/** Full-width hairline divider used between member rows. */
@Composable
fun CommunityDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
    )
}

@Preview(showBackground = true)
@Composable
private fun CommunityCardPreview() {
    LanguageLearningCommunityTheme {
        Column {
            CommunityCard(
                member = CommunityMember(
                    id = 1,
                    topic = "I can help you learn English and Spanish.",
                    firstName = "Jonathan",
                    pictureUrl = "",
                    natives = listOf("en"),
                    learns = listOf("ru"),
                    referenceCount = 12,
                    isLiked = false,
                ),
                onLikeToggled = {},
            )
            CommunityDivider()
            CommunityCard(
                member = CommunityMember(
                    id = 2,
                    topic = "I can help you learn English and Spanish.",
                    firstName = "Martina",
                    pictureUrl = "",
                    natives = listOf("es"),
                    learns = listOf("ru"),
                    referenceCount = 0,
                    isLiked = true,
                ),
                onLikeToggled = {},
            )
        }
    }
}
