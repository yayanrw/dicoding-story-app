package com.heyproject.storyapp.data.remote.dto

import com.heyproject.storyapp.domain.User
import com.squareup.moshi.Json


data class LoginResultDto(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "userId")
    val userId: String? = null,

    @Json(name = "token")
    val token: String? = null
) {
    fun toLoginUser(loginResult: LoginResultDto): User {
        return User(
            userId = loginResult.userId!!,
            name = loginResult.name!!,
            token = loginResult.token!!,
            isLogin = true
        )
    }
}
