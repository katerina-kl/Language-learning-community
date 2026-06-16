package com.tandem.community.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A row means "liked"; absence means "not liked". This keeps the
 * like state on the device across relaunches.
 */
@Entity(tableName = "liked_members")
data class LikedMemberEntity(
    @PrimaryKey val memberId: Int,
)
