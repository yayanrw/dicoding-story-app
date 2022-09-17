package com.heyproject.storyapp.data.network.response

import com.heyproject.storyapp.data.remote.dto.ListStoryDto
import com.squareup.moshi.Json

data class StoriesResponse(

    @Json(name = "listStory")
    val listStory: List<ListStoryDto>? = null,

    @Json(name = "error")
    val error: Boolean? = null,

    @Json(name = "message")
    val message: String? = null
)

