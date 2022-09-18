package com.heyproject.storyapp.data.remote.dto

import com.heyproject.storyapp.domain.model.LoginResult
import com.squareup.moshi.Json

data class LoginResultDto(
    @Json(name = "name") val name: String? = null,

    @Json(name = "userId") val userId: String? = null,

    @Json(name = "token") val token: String? = null
) {
    fun toLoginResult(): LoginResult {
        return LoginResult(
            name = name, userId = userId, token = token
        )
    }
}
