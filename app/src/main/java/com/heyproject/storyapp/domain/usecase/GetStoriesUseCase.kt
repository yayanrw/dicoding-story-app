package com.heyproject.storyapp.domain.usecase

import com.heyproject.storyapp.base.arch.BaseUseCase
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.base.wrapper.ViewResource
import com.heyproject.storyapp.domain.model.Story
import com.heyproject.storyapp.domain.model.StoryParams
import com.heyproject.storyapp.domain.model.toStory
import com.heyproject.storyapp.domain.repository.StoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 06:38
Github : https://github.com/yayanrw
 **/

class GetStoriesUseCase(
    private val storyRepository: StoryRepository,
    private val dispatcher: CoroutineDispatcher
) : BaseUseCase<StoryParams, List<Story>?>(dispatcher) {
    override suspend fun execute(param: StoryParams?): Flow<ViewResource<List<Story>?>> {
        return storyRepository.getStories(param!!).map { networkResult ->
            when (networkResult) {
                is DataResource.Success -> {
                    ViewResource.Success(networkResult.data?.listStory?.map {
                        it.toStory()
                    })
                }
                is DataResource.Error -> {
                    ViewResource.Error(networkResult.exception)
                }
            }
        }.onStart { emit(ViewResource.Loading()) }
    }
}