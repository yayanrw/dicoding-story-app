package com.heyproject.storyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heyproject.storyapp.domain.model.Story

@Entity
data class StoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
    val lon: Double,
    val lat: Double
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
}