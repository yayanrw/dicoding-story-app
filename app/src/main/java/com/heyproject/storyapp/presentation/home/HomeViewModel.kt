package com.heyproject.storyapp.presentation.home

import androidx.lifecycle.*
import com.heyproject.storyapp.domain.model.User
import kotlinx.coroutines.launch

class HomeViewModel(
//    val getStoriesUseCase: GetStoriesUseCase,
//    val
) : ViewModel() {
//    private val _requestState = MutableLiveData<RequestState>()
//    val requestState: LiveData<RequestState> = _requestState
//
//    private val _stories = MutableLiveData<List<ListStoryItem>?>()
//    val stories: LiveData<List<ListStoryItem>?> = _stories

    fun fetchStories() {
        viewModelScope.launch {
//            try {
//                val token = pref.getUser().first().token
//                _requestState.value = RequestState.LOADING
//                val responseListStories = StoryApi.retrofitService.getStories(
//                    1,
//                    10,
//                    0,
//                    "Bearer $token"
//                ).listStory
//                _stories.value = responseListStories
//                if (responseListStories.isNullOrEmpty()) {
//                    _requestState.value = RequestState.NO_DATA
//                } else {
//                    _requestState.value = RequestState.SUCCESS
//                }
//            } catch (e: HttpException) {
//                _requestState.value = RequestState.ERROR
//                _stories.value = listOf()
//            } catch (e: IOException) {
//                _requestState.value = RequestState.NO_CONNECTION
//                _stories.value = listOf()
//            }
        }
    }

//    fun getUser(): LiveData<User> {
//        return pref.getUser().asLiveData()
//    }

    fun logout() {
        viewModelScope.launch {
//            pref.logout()
        }
    }
}