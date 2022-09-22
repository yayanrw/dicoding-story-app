package com.heyproject.storyapp.data.datasource.remote.api

import com.heyproject.storyapp.data.datasource.remote.response.LoginResponse
import com.heyproject.storyapp.data.datasource.remote.response.StoriesResponse
import com.heyproject.storyapp.data.datasource.remote.response.GeneralResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StoryApi {
    @FormUrlEncoded
    @POST("register")
    suspend fun postRegister(
        @Field("name") name: String? = null,
        @Field("email") email: String? = null,
        @Field("password") password: String? = null
    ): GeneralResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") email: String? = null,
        @Field("password") password: String? = null
    ): LoginResponse

    @Multipart
    @POST("stories")
    suspend fun postStory(
        @Part photo: MultipartBody.Part? = null,
        @Part("description") description: RequestBody? = null,
        @Header("Authorization") auth: String? = null
    ): GeneralResponse

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int? = 1,
        @Query("size") size: Int? = 10,
        @Query("location") location: Int? = 1,
        @Header("Authorization") auth: String? = null
    ): StoriesResponse
}