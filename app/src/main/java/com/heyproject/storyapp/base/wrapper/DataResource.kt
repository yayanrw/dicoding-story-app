package com.heyproject.storyapp.base.wrapper

/**
Written by Yayan Rahmat Wijaya on 9/22/2022 17:20
Github : https://github.com/yayanrw
 **/

sealed class DataResource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : DataResource<T>(data, exception = exception)
}