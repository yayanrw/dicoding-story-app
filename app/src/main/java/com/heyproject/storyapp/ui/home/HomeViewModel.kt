package com.heyproject.storyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.storyapp.network.StoryApi
import com.heyproject.storyapp.network.response.ListStoryItem
import com.heyproject.storyapp.util.RequestState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _stories = MutableLiveData<List<ListStoryItem>?>()
    val stories: LiveData<List<ListStoryItem>?> = _stories

    init {
        getStories()
    }

    private fun getStories() {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val stories = StoryApi.retrofitService.getStories(1, 10, 0)
                _stories.value = stories.listStory
                _requestState.value = RequestState.SUCCESS
            } catch (e: HttpException) {
                _requestState.value = RequestState.ERROR
                _message.value = "Oops, something went wrong!"
                _stories.value = listOf()
            } catch (e: IOException) {
                _requestState.value = RequestState.ERROR
                _message.value = "Couldn't reach server, check your internet connection."
                _stories.value = listOf()
            }
        }
    }
}