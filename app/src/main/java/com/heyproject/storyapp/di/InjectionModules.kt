package com.heyproject.storyapp.di

import com.heyproject.storyapp.data.datasource.local.LocalDataSource
import com.heyproject.storyapp.data.datasource.local.LocalDataSourceImpl
import com.heyproject.storyapp.data.datasource.local.database.StoryDatabase
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSource
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSourceImpl
import com.heyproject.storyapp.data.repository.StoryRepositoryImpl
import com.heyproject.storyapp.data.repository.UserPreferencesRepositoryImpl
import com.heyproject.storyapp.domain.repository.StoryRepository
import com.heyproject.storyapp.domain.repository.UserPreferencesRepository
import com.heyproject.storyapp.domain.usecase.GetStoriesUseCase
import com.heyproject.storyapp.domain.usecase.PostLoginUseCase
import com.heyproject.storyapp.domain.usecase.PostRegisterUseCase
import com.heyproject.storyapp.domain.usecase.PostStoryUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 10:42
Github : https://github.com/yayanrw
 **/

object InjectionModules {
    fun getModules() = listOf(
        networkModule,
        dataSourceModule,
        repositoryModule,
        dataBaseModule,
        useCaseModule,
        viewModelModule
    )

    private val networkModule = module {
        single { }
    }

    private val dataSourceModule = module {
        single<LocalDataSource> { LocalDataSourceImpl(get(), get()) }
        single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<StoryRepository> { StoryRepositoryImpl(get(), get()) }
        single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }
    }

    private val dataBaseModule = module {
        single { get<StoryDatabase>().storyDao() }
    }

    private val useCaseModule = module {
        single { GetStoriesUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostLoginUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostRegisterUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostStoryUseCase(get(), dispatcher = Dispatchers.IO) }
    }

    private val viewModelModule = module {

    }
}