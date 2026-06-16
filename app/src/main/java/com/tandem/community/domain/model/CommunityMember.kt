package com.tandem.community.domain.model

/**
 * Represents a community member in the app.
 *
 * This model is kept separate from network and database models so other layers
 * don't depend on data implementation details.
 *
 * isNew is true when the API returns referenceCnt = 0.
 * isLiked is stored only locally on the device.
 */
data class CommunityMember(
    val id: Int,
    val topic: String,
    val firstName: String,
    val pictureUrl: String,
    val natives: List<String>,
    val learns: List<String>,
    val referenceCount: Int,
    val isLiked: Boolean = false,
) {
    val isNew: Boolean get() = referenceCount == 0
}
