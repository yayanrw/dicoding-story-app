package com.heyproject.storyapp.domain.model

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class PostStoryParams(
    val photo: MultipartBody.Part,
    val description: RequestBody,
    val auth: String
)