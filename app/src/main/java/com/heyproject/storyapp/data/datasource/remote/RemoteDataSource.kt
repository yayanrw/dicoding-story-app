package com.heyproject.storyapp.data.datasource.remote

import com.heyproject.storyapp.data.datasource.remote.api.StoryApi
import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse
import com.heyproject.storyapp.data.datasource.remote.response.LoginResponse
import com.heyproject.storyapp.data.datasource.remote.response.StoriesResponse
import com.heyproject.storyapp.domain.model.PostStoryParams
import com.heyproject.storyapp.domain.model.StoryParams
import com.heyproject.storyapp.domain.model.UserModel

class RemoteDataSourceImpl(private val storyApi: StoryApi) : RemoteDataSource {
    override suspend fun getStories(storyParams: StoryParams): StoriesResponse =
        storyApi.getStories(
            storyParams.page,
            storyParams.size,
            storyParams.location,
            storyParams.auth
        )

    override suspend fun postLogin(user: UserModel): LoginResponse =
        storyApi.postLogin(user.email, user.password)

    override suspend fun postRegister(user: UserModel): GeneralResponse =
        storyApi.postRegister(user.name, user.email, user.password)

    override suspend fun postStory(postStoryParams: PostStoryParams): GeneralResponse =
        storyApi.postStory(postStoryParams.photo, postStoryParams.description, postStoryParams.auth)

}

interface RemoteDataSource {
    suspend fun getStories(storyParams: StoryParams): StoriesResponse
    suspend fun postLogin(user: UserModel): LoginResponse
    suspend fun postRegister(user: UserModel): GeneralResponse
    suspend fun postStory(postStoryParams: PostStoryParams): GeneralResponse
}