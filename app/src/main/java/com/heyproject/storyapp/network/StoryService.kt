package com.heyproject.storyapp.network

import com.heyproject.storyapp.network.response.GeneralResponse
import com.heyproject.storyapp.network.response.LoginResponse
import com.heyproject.storyapp.network.response.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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

    @Multipart
    @POST("stories/guest")
    suspend fun insertGuestStory(
        @Part photo: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lon") lon: RequestBody?,
        @Part("lat") lat: RequestBody?
    ): GeneralResponse

    @Multipart
    @POST("stories")
    suspend fun insertStory(
        @Part photo: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lon") lon: RequestBody?,
        @Part("lat") lat: RequestBody?
    ): GeneralResponse

    @GET("stories?page={page}&size={size}&location={location}")
    suspend fun getStories(
        @Path("page") page: Int,
        @Path("size") size: Int,
        @Path("location") location: Int
    ) : StoriesResponse
}