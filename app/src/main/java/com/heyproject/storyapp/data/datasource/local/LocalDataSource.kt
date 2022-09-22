package com.heyproject.storyapp.data.datasource.local

import com.heyproject.storyapp.data.datasource.local.dao.StoryDao
import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity
import com.heyproject.storyapp.domain.StoryParams

class LocalDataSourceImpl(private val storyDao: StoryDao) : LocalDataSource {
    override suspend fun getStories(storyParams: StoryParams): List<StoryEntity> =
        storyDao.getStories()

    override suspend fun insertStories(stories: List<StoryEntity>) = storyDao.insertStory(stories)
}

interface LocalDataSource {
    suspend fun getStories(storyParams: StoryParams): List<StoryEntity>
    suspend fun insertStories(stories: List<StoryEntity>)
}