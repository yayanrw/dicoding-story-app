package com.heyproject.storyapp.domain

data class User(
    var userId: String,
    var name: String,
    var token: String,
    var isLogin: Boolean
)
