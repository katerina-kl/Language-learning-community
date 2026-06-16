package com.tandem.community.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** Top-level network envelope returned by the community endpoint. */
@JsonClass(generateAdapter = true)
data class CommunityResponseDto(
    @Json(name = "response") val response: List<CommunityMemberDto>?,
    @Json(name = "errorCode") val errorCode: String?,
    @Json(name = "type") val type: String?,
)
