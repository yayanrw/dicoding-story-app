package com.heyproject.storyapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.storyapp.network.StoryApi
import com.heyproject.storyapp.util.RequestState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "RegisterViewModel"

class RegisterViewModel : ViewModel() {
    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _requestState.value = RequestState.LOADING
                val response =
                    StoryApi.retrofitService.postRegister(
                        name,
                        email,
                        password
                    )
                if (!response.error!!) {
                    _requestState.value = RequestState.SUCCESS
                    _message.value = "Success, ${response.message}. Please login to continue."
                } else {
                    _requestState.value = RequestState.ERROR
                    _message.value = response.message
                }
            } catch (e: HttpException) {
                _requestState.value = RequestState.ERROR
                if (e.code() == 400) {
                    _message.value = "Email is already taken"
                } else {
                    _message.value = "Oops, something went wrong!"
                }
                Log.e(TAG, e.toString())
            } catch (e: IOException) {
                _requestState.value = RequestState.ERROR
                _message.value = "Couldn't reach server, check your internet connection."
                Log.e(TAG, e.toString())
            }
        }
    }
}