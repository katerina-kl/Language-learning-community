package com.tandem.community.util

import com.tandem.community.data.local.LikedMemberDao
import com.tandem.community.data.local.LikedMemberEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeLikedMemberDao : LikedMemberDao {

    private val ids = MutableStateFlow<List<Int>>(emptyList())

    override fun observeLikedIds(): Flow<List<Int>> = ids

    override suspend fun like(entity: LikedMemberEntity) {
        ids.update { (it + entity.memberId).distinct() }
    }

    override suspend fun unlike(memberId: Int) {
        ids.update { it - memberId }
    }
}
