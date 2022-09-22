package com.heyproject.storyapp.domain.model

import com.heyproject.storyapp.data.datasource.remote.dto.LoginResultDto

data class LoginResult(
    val name: String?,
    val userId: String?,
    val token: String?
)

fun LoginResultDto.toLoginResult() =
    LoginResult(name = this.name.orEmpty(), userId.orEmpty(), token.orEmpty())