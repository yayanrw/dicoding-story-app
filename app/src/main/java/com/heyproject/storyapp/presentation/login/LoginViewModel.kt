package com.heyproject.storyapp.presentation.login

import androidx.lifecycle.*
import com.heyproject.storyapp.data.datasource.remote.api.StoryApi
import com.heyproject.storyapp.domain.model.LoginResult
import com.heyproject.storyapp.domain.model.User
import com.heyproject.storyapp.domain.usecase.PostLoginUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel(
    private val postLoginUseCase: PostLoginUseCase
) : ViewModel() {
//    private val _requestState = MutableLiveData<RequestState>()
//    val requestState: LiveData<RequestState> = _requestState
//
    fun signIn(user: User) {
        viewModelScope.launch {
            try {
                postLoginUseCase(user).collect {
                    println(it)
                }
            } catch (e: HttpException) {
                println(e.message())
            } catch (e: IOException) {
                println(e.message)
            }
        }
    }
//
//    fun getUser(): LiveData<User> {
//        return pref.getUser().asLiveData()
//    }
}