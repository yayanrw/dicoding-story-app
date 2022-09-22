package com.heyproject.storyapp.base.exception

/**
Written by Yayan Rahmat Wijaya on 9/22/2022 17:26
Github : https://github.com/yayanrw
 **/

class ApiErrorException(override val message: String? = null, val httpCode: Int? = null) :
    Exception() {}