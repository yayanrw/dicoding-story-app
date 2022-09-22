package com.heyproject.storyapp.domain.repository

import com.heyproject.storyapp.base.arch.BaseContract
import com.heyproject.storyapp.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 04:14
Github : https://github.com/yayanrw
 **/

interface UserRepository : BaseContract.BaseRepository {
    fun getUser(): Flow<UserModel>
    suspend fun saveUser(user: UserModel)
    suspend fun logOut(user: UserModel)
}