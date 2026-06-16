package com.tandem.community.di

import android.content.Context
import androidx.room.Room
import com.tandem.community.data.local.AppDatabase
import com.tandem.community.data.local.LikedMemberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .build()

    @Provides
    fun provideLikedMemberDao(database: AppDatabase): LikedMemberDao =
        database.likedMemberDao()
}
