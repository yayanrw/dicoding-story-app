package com.heyproject.storyapp.data.remote.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class ListStoryDto(

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