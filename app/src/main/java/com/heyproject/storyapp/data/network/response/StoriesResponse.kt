package com.heyproject.storyapp.data.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class StoriesResponse(

    @Json(name = "listStory")
    val listStory: List<ListStoryItem>? = null,

    @Json(name = "error")
    val error: Boolean? = null,

    @Json(name = "message")
    val message: String? = null
)

@Parcelize
data class ListStoryItem(

    @Json(name = "photoUrl")
    val photoUrl: String? = null,

    @Json(name = "createdAt")
    val createdAt: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "lon")
    val lon: Double? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "lat")
    val lat: Double? = null
) : Parcelable
