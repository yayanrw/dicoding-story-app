package com.heyproject.storyapp.base.arch

import android.util.Log
import com.heyproject.storyapp.BuildConfig
import com.heyproject.storyapp.base.exception.ApiErrorException
import com.heyproject.storyapp.base.exception.NoInternetConnectionException
import com.heyproject.storyapp.base.exception.UnexpectedApiErrorException
import com.heyproject.storyapp.base.wrapper.DataResource
import retrofit2.HttpException
import java.io.IOException

/**
Written by Yayan Rahmat Wijaya on 9/22/2022 17:34
Github : https://github.com/yayanrw
 **/

open class BaseRepositoryImpl : BaseContract.BaseRepository {
    override fun logResponse(msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(BaseRepositoryImpl::class.java.simpleName, msg.orEmpty())
        }
    }

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResource.Error(NoInternetConnectionException())
                is HttpException -> {
                    when (val code = throwable.code()) {
                        in 300..308 -> {
                            DataResource.Error(ApiErrorException("Redirect", code))
                        }
                        in 400..451 -> {
                            DataResource.Error(ApiErrorException("Client Error", code))
                        }
                        in 500..511 -> {
                            DataResource.Error(ApiErrorException("Server Error", code))
                        }
                        else -> {
                            DataResource.Error(ApiErrorException(httpCode = code))
                        }
                    }
                }
                else -> {
                    DataResource.Error(UnexpectedApiErrorException())
                }
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            DataResource.Error(exception)
        }
    }
}