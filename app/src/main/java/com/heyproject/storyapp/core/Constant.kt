package com.heyproject.storyapp.core

import android.Manifest

const val BASE_URL = "https://story-api.dicoding.dev/v1/"
const val MIN_PASSWORD_LENGTH = 6
const val CAMERA_RESULT = "picture"
const val IS_BACK_CAMERA_RESULT = "isBackCamera"

val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
const val REQUEST_CODE_PERMISSIONS = 10