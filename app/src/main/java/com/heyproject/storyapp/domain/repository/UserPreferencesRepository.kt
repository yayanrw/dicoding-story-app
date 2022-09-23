package com.heyproject.storyapp.domain.repository

import com.heyproject.storyapp.base.arch.BaseContract
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 04:14
Github : https://github.com/yayanrw
 **/

interface UserPreferencesRepository : BaseContract.BaseRepository {
    suspend fun getUser(): Flow<DataResource<User>>
    suspend fun saveUser(user: User): Flow<DataResource<Boolean>>
    suspend fun removeUser(user: User): Flow<DataResource<Boolean>>
}