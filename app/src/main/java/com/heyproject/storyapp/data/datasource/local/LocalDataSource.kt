package com.heyproject.storyapp.data.datasource.local

import com.heyproject.storyapp.data.datasource.local.dao.StoryDao
import com.heyproject.storyapp.data.datasource.local.datastore.UserDataStore
import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity
import com.heyproject.storyapp.domain.model.StoryParams
import com.heyproject.storyapp.domain.model.User
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val storyDao: StoryDao,
    private val userDataStore: UserDataStore
) : LocalDataSource {
    override suspend fun getStories(storyParams: StoryParams): List<StoryEntity> =
        storyDao.getStories()

    override suspend fun insertStories(stories: List<StoryEntity>) = storyDao.insertStory(stories)
    override suspend fun getUser(): Flow<User> = userDataStore.getUser()

    override suspend fun saveUser(user: User): Boolean = userDataStore.saveUser(user)

    override suspend fun removeUser(): Boolean = userDataStore.removeUser()
}

interface LocalDataSource {
    suspend fun getStories(storyParams: StoryParams): List<StoryEntity>
    suspend fun insertStories(stories: List<StoryEntity>)
    suspend fun getUser(): Flow<User>
    suspend fun saveUser(user: User): Boolean
    suspend fun removeUser(): Boolean
}