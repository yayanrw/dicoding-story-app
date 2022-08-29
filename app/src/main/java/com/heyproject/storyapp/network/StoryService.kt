package com.heyproject.storyapp.network

import com.heyproject.storyapp.network.response.GeneralResponse
import com.heyproject.storyapp.network.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StoryService {
    @FormUrlEncoded
    @POST("register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): GeneralResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}