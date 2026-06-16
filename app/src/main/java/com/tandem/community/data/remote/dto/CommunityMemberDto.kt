package com.tandem.community.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** Network representation of a community member as returned by the API. */
@JsonClass(generateAdapter = true)
data class CommunityMemberDto(
    @Json(name = "id") val id: Int,
    @Json(name = "topic") val topic: String?,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "pictureUrl") val pictureUrl: String?,
    @Json(name = "natives") val natives: List<String>?,
    @Json(name = "learns") val learns: List<String>?,
    @Json(name = "referenceCnt") val referenceCnt: Int?,
)
