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

    private val _stories = MutableLiveData<List<ListStoryItem>?>()
    val stories: LiveData<List<ListStoryItem>?> = _stories

    fun getStoryList(token: String) {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val responseListStories = StoryApi.retrofitService.getStories(
                    1,
                    10,
                    0,
                    "Bearer $token"
                ).listStory
                _stories.value = responseListStories
                if (responseListStories.isNullOrEmpty()) {
                    _requestState.value = RequestState.NO_DATA
                } else {
                    _requestState.value = RequestState.SUCCESS
                }
            } catch (e: HttpException) {
                _requestState.value = RequestState.ERROR
                _stories.value = listOf()
            } catch (e: IOException) {
                _requestState.value = RequestState.NO_CONNECTION
                _stories.value = listOf()
            }
        }
    }
}