package com.heyproject.storyapp.data.datasource.remote.dto

import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity
import com.heyproject.storyapp.domain.model.Story
import com.squareup.moshi.Json

data class StoryDto(
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
) {
    fun toStory(): Story {
        return Story(
            id = id,
            name = name,
            description = description,
            photoUrl = photoUrl,
            lon = lon,
            lat = lat,
            createdAt = createdAt
        )
    }

    fun toStoryEntity(): StoryEntity {
        return StoryEntity(
            id = id,
            name = name,
            description = description,
            photoUrl = photoUrl,
            createdAt = createdAt,
            lon = lon,
            lat = lat
        )
    }
}