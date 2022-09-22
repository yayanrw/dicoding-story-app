package com.heyproject.storyapp.data.repository

import com.heyproject.storyapp.base.arch.BaseRepositoryImpl
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.data.datasource.local.LocalDataSource
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSource
import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse
import com.heyproject.storyapp.data.datasource.remote.response.LoginResponse
import com.heyproject.storyapp.data.datasource.remote.response.StoriesResponse
import com.heyproject.storyapp.domain.model.PostStoryParams
import com.heyproject.storyapp.domain.model.StoryParams
import com.heyproject.storyapp.domain.model.User
import com.heyproject.storyapp.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoryRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : BaseRepositoryImpl(), StoryRepository {
    override suspend fun getStories(storyParams: StoryParams): Flow<DataResource<StoriesResponse>> =
        flow {
            emit(safeNetworkCall { remoteDataSource.getStories(storyParams) })
        }

    override suspend fun postRegister(user: User): Flow<DataResource<GeneralResponse>> =
        flow {
            emit(safeNetworkCall { remoteDataSource.postRegister(user) })
        }

    override suspend fun postLogin(user: User): Flow<DataResource<LoginResponse>> =
        flow {
            emit(safeNetworkCall { remoteDataSource.postLogin(user) })
        }

    override suspend fun postStory(postStoryParams: PostStoryParams): Flow<DataResource<GeneralResponse>> =
        flow {
            emit(safeNetworkCall { remoteDataSource.postStory(postStoryParams) })
        }
}