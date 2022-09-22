package com.heyproject.storyapp.base

import android.Manifest

const val MIN_PASSWORD_LENGTH = 6
const val CAMERA_RESULT = "picture"
const val IS_BACK_CAMERA_RESULT = "isBackCamera"

val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
const val REQUEST_CODE_PERMISSIONS = 10
const val HOST_NAME = "story-api.dicoding.dev"