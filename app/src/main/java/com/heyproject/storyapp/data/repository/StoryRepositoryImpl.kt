package com.heyproject.storyapp.data.repository

import com.heyproject.storyapp.base.Resource
import com.heyproject.storyapp.data.datasource.local.LocalDataSource
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSource
import com.heyproject.storyapp.data.datasource.remote.dto.LoginResultDto
import com.heyproject.storyapp.data.datasource.remote.dto.StoryDto
import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse
import com.heyproject.storyapp.data.util.ApiResponse
import com.heyproject.storyapp.data.util.NetworkBoundResource
import com.heyproject.storyapp.domain.PostStoryParams
import com.heyproject.storyapp.domain.StoryParams
import com.heyproject.storyapp.domain.UserModel
import com.heyproject.storyapp.domain.model.Story
import com.heyproject.storyapp.domain.repository.StoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class StoryRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : StoryRepository {
    override fun getStories(storyParams: StoryParams): Flow<Resource<List<Story>>> =
        object : NetworkBoundResource<List<Story>, List<StoryDto>>() {
            override fun loadFromDB(): Flow<List<Story>> {
                return localDataSource.getStories().map { stories ->
                    stories.map { it.toStory() }
                }
            }

            override fun shouldFetch(data: List<Story>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<StoryDto>>> {
                return remoteDataSource.getStories(storyParams)
            }

            override suspend fun saveCallResult(data: List<StoryDto>) {
                localDataSource.insertStories(data.map { it.toStoryEntity() })
            }
        }.asFlow()

    override suspend fun postRegister(user: UserModel): Flow<Resource<GeneralResponse>> {
        return remoteDataSource.postRegister(user).flowOn(Dispatchers.IO)
    }

    override suspend fun postLogin(user: UserModel): Flow<Resource<LoginResultDto>> {
        return remoteDataSource.postLogin(user).flowOn(Dispatchers.IO)
    }

    override suspend fun postStory(postStoryParams: PostStoryParams): Flow<Resource<GeneralResponse>> {
        return remoteDataSource.postStory(postStoryParams).flowOn(Dispatchers.IO)
    }
}