package com.heyproject.storyapp.presentation.register

import androidx.lifecycle.ViewModel


class RegisterViewModel() : ViewModel() {
//    private val _requestState = MutableLiveData<RequestState>()
//    val requestState: LiveData<RequestState> = _requestState

//    fun register(name: String, email: String, password: String) {
//        viewModelScope.launch {
//            try {
//                _requestState.value = RequestState.LOADING
//                val response =
//                    StoryApi.retrofitService.postRegister(
//                        name,
//                        email,
//                        password
//                    )
//                if (!response.error!!) {
//                    _requestState.value = RequestState.SUCCESS
//                } else {
//                    _requestState.value = RequestState.ERROR
//                }
//            } catch (e: HttpException) {
//                if (e.code() == 400) {
//                    _requestState.value = RequestState.EMAIL_TAKEN
//                } else {
//                    _requestState.value = RequestState.ERROR
//                }
//            } catch (e: IOException) {
//                _requestState.value = RequestState.NO_CONNECTION
//            }
//        }
//    }
//
//    fun getUser(): LiveData<User> {
//        return pref.getUser().asLiveData()
//    }
}