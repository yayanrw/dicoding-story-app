package com.heyproject.storyapp.data.datasource.local.database

import androidx.room.Database
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
}