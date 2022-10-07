package com.heyproject.storyapp.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heyproject.storyapp.data.datasource.local.dao.StoryDao
import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity

@Database(
    entities = [
        StoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao

    companion object {
        private const val DB_NAME = "story_db"
        fun create(context: Context): StoryDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                StoryDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}