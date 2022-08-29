package com.heyproject.storyapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.storyapp.network.StoryApi
import com.heyproject.storyapp.util.RequestState
import kotlinx.coroutines.launch

const val TAG = "RegisterViewModel"

class RegisterViewModel : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val response =
                    StoryApi.retrofitService.register(
                        name,
                        email,
                        password
                    )
                _requestState.value = RequestState.SUCCESS
                Log.d(TAG, response.toString())


            } catch (e: Exception) {
                _requestState.value = RequestState.ERROR
                _message.value = e.toString()
                Log.e(TAG, e.toString())
            }
        }
    }
}