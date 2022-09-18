package com.heyproject.storyapp.domain.repository

import com.heyproject.storyapp.common.Resource
import com.heyproject.storyapp.domain.PostStoryParams
import com.heyproject.storyapp.domain.StoryParams
import com.heyproject.storyapp.domain.UserModel
import com.heyproject.storyapp.domain.model.General
import com.heyproject.storyapp.domain.model.LoginResult
import com.heyproject.storyapp.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    fun getStories(storyParams: StoryParams): Flow<Resource<List<Story>>>
    fun postRegister(user: UserModel): Flow<Resource<General>>
    fun postLogin(user: UserModel): Flow<Resource<LoginResult>>
    fun postStory(postStoryParams: PostStoryParams): Flow<Resource<General>>
}