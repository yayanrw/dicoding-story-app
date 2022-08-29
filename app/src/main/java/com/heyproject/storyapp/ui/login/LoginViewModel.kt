package com.heyproject.storyapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.storyapp.network.StoryApi
import com.heyproject.storyapp.network.response.LoginResult
import com.heyproject.storyapp.util.RequestState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "LoginViewModel"

class LoginViewModel : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val response = StoryApi.retrofitService.postLogin(email, password)
                if (!response.error!!) {
                    _requestState.value = RequestState.SUCCESS
                    _loginResult.value = response.loginResult!!
                    _message.value = response.message ?: "Login successfully"
                } else {
                    _requestState.value = RequestState.ERROR
                    _message.value = response.message ?: "Oops, something went wrong!"
                }

            } catch (e: HttpException) {
                _requestState.value = RequestState.ERROR
                if (e.code() == 401) {
                    _message.value = "Invalid password"
                } else {
                    _message.value = "Oops, something went wrong!"
                }
                Log.e(TAG, e.toString())
            } catch (e: IOException) {
                _requestState.value = RequestState.ERROR
                _message.value = "Couldn't reach server, check your internet connection."
                Log.e(com.heyproject.storyapp.ui.register.TAG, e.toString())
            }
        }
    }
}