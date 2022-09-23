package com.heyproject.storyapp.domain.usecase

import com.heyproject.storyapp.base.arch.BaseUseCase
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.base.wrapper.ViewResource
import com.heyproject.storyapp.domain.model.General
import com.heyproject.storyapp.domain.model.User
import com.heyproject.storyapp.domain.model.toGeneral
import com.heyproject.storyapp.domain.repository.StoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 09:43
Github : https://github.com/yayanrw
 **/

class PostRegisterUseCase(
    private val storyRepository: StoryRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<User, General>(dispatcher) {
    override suspend fun execute(param: User?): Flow<ViewResource<General>> {
        return storyRepository.postRegister(param!!).map { networkResult ->
            when (networkResult) {
                is DataResource.Success -> {
                    ViewResource.Success(networkResult.data.toGeneral())
                }
                is DataResource.Error -> {
                    ViewResource.Error(networkResult.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }
    }
}