package com.heyproject.storyapp.domain.model

import android.os.Parcelable
import com.heyproject.storyapp.data.datasource.local.entity.StoryEntity
import com.heyproject.storyapp.data.datasource.remote.dto.StoryDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val id: String?,
    val name: String?,
    val description: String?,
    val photoUrl: String?,
    val lon: Double?,
    val lat: Double?,
    val createdAt: String?
) : Parcelable

fun Story?.toEntity() = StoryEntity(
    id = this?.id.orEmpty(),
    name = this?.name,
    description = this?.description,
    photoUrl = this?.photoUrl,
    createdAt = this?.createdAt,
    lon = this?.lon,
    lat = this?.lat
)

fun StoryEntity?.toStory() = Story(
    id = this?.id.orEmpty(),
    name = this?.name.orEmpty(),
    description = this?.description.orEmpty(),
    photoUrl = this?.photoUrl.orEmpty(),
    lon = this?.lon ?: 0.0,
    lat = this?.lat ?: 0.0,
    createdAt = this?.createdAt.orEmpty()
)

fun List<StoryEntity>?.mapToViewParams() = mutableListOf<Story>().apply {
    addAll(this@mapToViewParams?.map {
        it.toStory()
    } ?: listOf())
}

fun StoryDto?.toStory() = Story(
    id = this?.id.orEmpty(),
    name = this?.name.orEmpty(),
    description = this?.description.orEmpty(),
    photoUrl = this?.photoUrl.orEmpty(),
    lon = this?.lon ?: 0.0,
    lat = this?.lat ?: 0.0,
    createdAt = this?.createdAt.orEmpty()
)