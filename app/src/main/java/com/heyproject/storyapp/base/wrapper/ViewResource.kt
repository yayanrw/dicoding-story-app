package com.heyproject.storyapp.base.wrapper

/**
Written by Yayan Rahmat Wijaya on 9/22/2022 17:22
Github : https://github.com/yayanrw
 **/

sealed class ViewResource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : ViewResource<T>(data)
    class Empty<T>(data: T? = null, msg: String? = null) : ViewResource<T>(data)
    class Loading<T>(data: T? = null) : ViewResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) :
        ViewResource<T>(data, exception = exception)
}