package com.heyproject.storyapp.data.repository

import com.heyproject.storyapp.base.arch.BaseRepositoryImpl
import com.heyproject.storyapp.base.wrapper.DataResource
import com.heyproject.storyapp.data.datasource.local.LocalDataSource
import com.heyproject.storyapp.domain.model.User
import com.heyproject.storyapp.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 04:51
Github : https://github.com/yayanrw
 **/

class UserPreferencesRepositoryImpl(private val localDataSource: LocalDataSource) :
    BaseRepositoryImpl(), UserPreferencesRepository {
    override fun getUser(): Flow<DataResource<User>> =
        flow {
            emit(proceed { localDataSource.getUser() })
        }

    override suspend fun saveUser(user: User): Flow<DataResource<Boolean>> =
        flow {
            emit(proceed { localDataSource.saveUser(user) })
        }

    override suspend fun removeUser(user: User): Flow<DataResource<Boolean>> =
        flow {
            emit(proceed { localDataSource.removeUser(user) })
        }
}