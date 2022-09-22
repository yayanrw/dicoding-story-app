package com.heyproject.storyapp.data.datasource.remote.response

import com.heyproject.storyapp.data.datasource.remote.dto.StoryDto
import com.squareup.moshi.Json

data class StoriesResponse(
    @Json(name = "listStory")
    val listStory: List<StoryDto>? = null,

    @Json(name = "error")
    val error: Boolean? = null,

    @Json(name = "message")
    val message: String? = null
)

