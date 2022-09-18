package com.heyproject.storyapp.domain.repository

import com.heyproject.storyapp.common.Resource
import com.heyproject.storyapp.data.remote.dto.LoginResultDto
import com.heyproject.storyapp.data.remote.dto.StoryDto
import com.heyproject.storyapp.data.remote.response.GeneralResponse
import com.heyproject.storyapp.domain.PostStoryParams
import com.heyproject.storyapp.domain.StoryParams
import com.heyproject.storyapp.domain.UserModel
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    fun getStories(storyParams: StoryParams): Flow<Resource<List<StoryDto>>>
    fun postRegister(user: UserModel): Flow<Resource<GeneralResponse>>
    fun postLogin(user: UserModel): Flow<Resource<LoginResultDto>>
    fun postStory(postStoryParams: PostStoryParams): Flow<Resource<GeneralResponse>>
}