package com.heyproject.storyapp.di

import androidx.room.Room
import com.heyproject.storyapp.BuildConfig
import com.heyproject.storyapp.data.datasource.local.LocalDataSource
import com.heyproject.storyapp.data.datasource.local.LocalDataSourceImpl
import com.heyproject.storyapp.data.datasource.local.database.StoryDatabase
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSource
import com.heyproject.storyapp.data.datasource.remote.RemoteDataSourceImpl
import com.heyproject.storyapp.data.datasource.remote.api.StoryApi
import com.heyproject.storyapp.data.repository.StoryRepositoryImpl
import com.heyproject.storyapp.data.repository.UserPreferencesRepositoryImpl
import com.heyproject.storyapp.domain.repository.StoryRepository
import com.heyproject.storyapp.domain.repository.UserPreferencesRepository
import com.heyproject.storyapp.domain.usecase.GetStoriesUseCase
import com.heyproject.storyapp.domain.usecase.PostLoginUseCase
import com.heyproject.storyapp.domain.usecase.PostRegisterUseCase
import com.heyproject.storyapp.domain.usecase.PostStoryUseCase
import com.heyproject.storyapp.presentation.home.HomeViewModel
import com.heyproject.storyapp.presentation.login.LoginViewModel
import com.heyproject.storyapp.presentation.register.RegisterViewModel
import com.heyproject.storyapp.presentation.story_add.StoryAddViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 10:42
Github : https://github.com/yayanrw
 **/

object InjectionModules {
    private const val BASE_URL = "https://story-api.dicoding.dev/v1/"
    private const val TIMEOUT_CONNECTION: Long = 120
    private const val DB_NAME = "StoryDB.db"

    fun getModules() = listOf(
        networkModule,
        dataBaseModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

    private val networkModule = module {
        single {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS).build()
        }
        single {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit =
                Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                    .baseUrl(BASE_URL).client(get()).build()
            retrofit.create(StoryApi::class.java)
        }
    }

    private val dataBaseModule = module {
        factory { get<StoryDatabase>().storyDao() }
        single {
            Room.databaseBuilder(
                androidContext(), StoryDatabase::class.java, DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

    private val dataSourceModule = module {
        single<LocalDataSource> { LocalDataSourceImpl(get(), get()) }
        single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<StoryRepository> { StoryRepositoryImpl(get(), get()) }
        single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }
    }

    private val useCaseModule = module {
        single { GetStoriesUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostLoginUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostRegisterUseCase(get(), dispatcher = Dispatchers.IO) }
        single { PostStoryUseCase(get(), dispatcher = Dispatchers.IO) }
    }

    private val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { StoryAddViewModel(get()) }
    }
}