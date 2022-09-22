package com.heyproject.storyapp.presentation.login

import androidx.lifecycle.*
import com.heyproject.storyapp.domain.model.User
import com.heyproject.storyapp.domain.model.UserPreference
import com.heyproject.storyapp.data.network.StoryApi
import com.heyproject.storyapp.data.network.response.LoginResult
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

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
                    pref.saveUser(LoginResult().toLoginUser(response.loginResult!!))
                } else {
                    _requestState.value = RequestState.ERROR
                }

            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _requestState.value = RequestState.INVALID_CREDENTIALS
                } else {
                    _requestState.value = RequestState.ERROR
                }
            } catch (e: IOException) {
                _requestState.value = RequestState.NO_CONNECTION
            }
        }
    }

    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }
}