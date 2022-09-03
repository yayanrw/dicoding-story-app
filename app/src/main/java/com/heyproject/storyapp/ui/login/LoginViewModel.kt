package com.heyproject.storyapp.ui.login

import android.content.Context
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
                } else {
                    _requestState.value = RequestState.ERROR
                }

            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _requestState.value = RequestState.INVALID_CREDENTIALS
                } else {
                    _requestState.value = RequestState.ERROR
                }
                Log.e(TAG, e.toString())
            } catch (e: IOException) {
                _requestState.value = RequestState.NO_CONNECTION
                Log.e(TAG, e.toString())
            }
        }
    }
}