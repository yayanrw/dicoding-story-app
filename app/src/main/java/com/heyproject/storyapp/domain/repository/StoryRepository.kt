package com.heyproject.storyapp.domain.repository

import com.heyproject.storyapp.base.arch.BaseContract
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse
import com.heyproject.storyapp.data.datasource.remote.response.LoginResponse
import com.heyproject.storyapp.data.datasource.remote.response.StoriesResponse
import com.heyproject.storyapp.domain.model.PostStoryParams
import com.heyproject.storyapp.domain.model.StoryParams
import com.heyproject.storyapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StoryRepository : BaseContract.BaseRepository {
    suspend fun getStories(storyParams: StoryParams): Flow<DataResource<StoriesResponse>>
    suspend fun postRegister(user: User): Flow<DataResource<GeneralResponse>>
    suspend fun postLogin(user: User): Flow<DataResource<LoginResponse>>
    suspend fun postStory(postStoryParams: PostStoryParams): Flow<DataResource<GeneralResponse>>
}