package com.heyproject.storyapp.data.remote

import com.heyproject.storyapp.common.Resource
import com.heyproject.storyapp.data.remote.api.StoryApi
import com.heyproject.storyapp.data.remote.dto.LoginResultDto
import com.heyproject.storyapp.data.remote.dto.StoryDto
import com.heyproject.storyapp.data.remote.response.GeneralResponse
import com.heyproject.storyapp.data.util.ApiResponse
import com.heyproject.storyapp.domain.PostStoryParams
import com.heyproject.storyapp.domain.StoryParams
import com.heyproject.storyapp.domain.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val storyApi: StoryApi) {
    suspend fun getStories(storyParams: StoryParams): Flow<ApiResponse<List<StoryDto>>> {
        return flow {
            try {
                val response = storyApi.getStories(
                    page = storyParams.page,
                    size = storyParams.size,
                    location = storyParams.location,
                    auth = storyParams.auth
                )
                if (response.listStory.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response.listStory!!))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postRegister(user: UserModel): Flow<Resource<GeneralResponse>> {
        return flow {
            try {
                val response = storyApi.postRegister(
                    name = user.name,
                    email = user.email,
                    password = user.password
                )
                if (response.error == false) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message.toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postLogin(user: UserModel): Flow<Resource<LoginResultDto>> {
        return flow {
            try {
                val response = storyApi.postLogin(
                    email = user.email,
                    password = user.password
                )
                if (response.error == false) {
                    emit(Resource.Success(response.loginResult!!))
                } else {
                    emit(Resource.Error(response.message.toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postStory(postStoryParams: PostStoryParams): Flow<Resource<GeneralResponse>> {
        return flow {
            try {
                val response = storyApi.postStory(
                    photo = postStoryParams.photo,
                    description = postStoryParams.description,
                    auth = postStoryParams.auth
                )
                if (response.error == false) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message.toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}