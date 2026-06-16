package com.tandem.community.data.mapper

import com.tandem.community.data.remote.dto.CommunityMemberDto
import com.tandem.community.domain.model.CommunityMember

/**
 * Translates network DTOs into clean domain models.
 *
 * Mapping lives in the data layer so the domain never has to know about the
 * wire format, and nullable network fields are normalised to safe defaults.
 */
fun CommunityMemberDto.toDomain(): CommunityMember = CommunityMember(
    id = id,
    topic = topic.orEmpty(),
    firstName = firstName.orEmpty(),
    pictureUrl = pictureUrl.orEmpty(),
    natives = natives.orEmpty(),
    learns = learns.orEmpty(),
    referenceCount = referenceCnt ?: 0,
)

fun List<CommunityMemberDto>.toDomain(): List<CommunityMember> = map { it.toDomain() }
