package com.heyproject.storyapp.presentation.story_add

import androidx.lifecycle.ViewModel

class StoryAddViewModel() : ViewModel() {
//    private val _requestState = MutableLiveData<RequestState>()
//    val requestState: LiveData<RequestState> = _requestState
//
//    fun uploadImage(getFile: File, description: String) {
//        viewModelScope.launch {
//            try {
//                val token = pref.getUser().first().token
//                _requestState.value = RequestState.LOADING
//                val file = reduceFileImage(getFile)
//                val descRequestBody = description.toRequestBody("text/plain".toMediaType())
//                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
//                    "photo",
//                    file.name,
//                    file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//                )
//                val response = StoryApi.retrofitService.insertStory(
//                    photo = imageMultipart,
//                    description = descRequestBody,
//                    auth = "Bearer $token"
//                )
//
//                if (!response.error!!) {
//                    _requestState.value = RequestState.SUCCESS
//                } else {
//                    _requestState.value = RequestState.ERROR
//                }
//            } catch (e: HttpException) {
//                _requestState.value = RequestState.ERROR
//            } catch (e: IOException) {
//                _requestState.value = RequestState.NO_CONNECTION
//            }
//        }
//    }
}