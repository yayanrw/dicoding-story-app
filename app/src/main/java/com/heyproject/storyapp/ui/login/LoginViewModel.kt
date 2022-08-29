package com.heyproject.storyapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.storyapp.network.StoryApi
import com.heyproject.storyapp.util.RequestState
import kotlinx.coroutines.launch

const val TAG = "LoginViewModel"

class LoginViewModel : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val response = StoryApi.retrofitService.postLogin(email, password)
                _requestState.value = RequestState.SUCCESS
            } catch (e: Exception) {
                _requestState.value = RequestState.ERROR
                _message.value = e.toString()
                Log.e(com.heyproject.storyapp.ui.register.TAG, e.toString())
            }
        }
    }
}