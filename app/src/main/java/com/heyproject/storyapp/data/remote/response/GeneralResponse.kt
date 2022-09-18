package com.heyproject.storyapp.data.remote.response

import com.heyproject.storyapp.domain.model.General
import com.squareup.moshi.Json

data class GeneralResponse(
    @Json(name = "error")
    val error: Boolean? = null,

    @Json(name = "message")
    val message: String? = null
) {
    fun toGeneral(): General {
        return General(error = error, message = message)
    }
}
