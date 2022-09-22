package com.heyproject.storyapp.domain.model

data class User(
    val userId: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    var token: String? = null,
    var isLogin: Boolean? = false
)
