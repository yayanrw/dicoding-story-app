package com.heyproject.storyapp.domain.model

data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val lon: Double,
    val lat: Double,
    val createdAt: String
)
