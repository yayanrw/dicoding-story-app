package com.heyproject.storyapp.domain.model

import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse

data class General(
    val error: Boolean?,
    val message: String?
)

fun GeneralResponse?.toGeneral() =
    General(error = this?.error ?: false, message = this?.message.orEmpty())
