package com.tandem.community.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LikedMemberEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun likedMemberDao(): LikedMemberDao

    companion object {
        const val NAME = "community.db"
    }
}
