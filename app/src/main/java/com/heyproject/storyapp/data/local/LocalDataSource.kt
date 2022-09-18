package com.heyproject.storyapp.data.local

import com.heyproject.storyapp.data.local.dao.StoryDao
import com.heyproject.storyapp.data.local.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val storyDao: StoryDao
) {
    fun getStories(): Flow<List<StoryEntity>> = storyDao.getStories()
    suspend fun insertStories(stories: List<StoryEntity>) = storyDao.insertStory(stories)
}