package com.heyproject.storyapp.domain

data class UserModel(
    val userId: String,
    val email: String,
    val name: String,
    val password: String,
    var token: String,
    var isLogin: Boolean
)
