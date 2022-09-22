package com.heyproject.storyapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    suspend fun getStories(): List<StoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(stories: List<StoryEntity>)
}