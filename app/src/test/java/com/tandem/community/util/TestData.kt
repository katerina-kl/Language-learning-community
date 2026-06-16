package com.tandem.community.util

import com.tandem.community.domain.model.CommunityMember

fun member(
    id: Int,
    firstName: String = "Member $id",
    referenceCount: Int = 5,
    natives: List<String> = listOf("de"),
    learns: List<String> = listOf("en"),
    isLiked: Boolean = false,
): CommunityMember = CommunityMember(
    id = id,
    topic = "Topic $id",
    firstName = firstName,
    pictureUrl = "https://example.com/$id.png",
    natives = natives,
    learns = learns,
    referenceCount = referenceCount,
    isLiked = isLiked,
)

fun members(count: Int, startId: Int = 1): List<CommunityMember> =
    (startId until startId + count).map { member(id = it) }
