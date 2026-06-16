package com.tandem.community.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedMemberDao {

    @Query("SELECT memberId FROM liked_members")
    fun observeLikedIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun like(entity: LikedMemberEntity)

    @Query("DELETE FROM liked_members WHERE memberId = :memberId")
    suspend fun unlike(memberId: Int)
}
