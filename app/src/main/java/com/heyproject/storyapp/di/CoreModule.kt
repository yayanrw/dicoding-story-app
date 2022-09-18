package com.heyproject.storyapp.di

import androidx.room.Room
import com.heyproject.storyapp.BuildConfig
import com.heyproject.storyapp.data.local.database.StoryDatabase
import com.heyproject.storyapp.data.remote.RemoteDataSource
import com.heyproject.storyapp.data.remote.api.StoryApi
import com.heyproject.storyapp.data.repository.StoryRepositoryImpl
import com.heyproject.storyapp.di.CoreModule.BASE_URL
import com.heyproject.storyapp.di.CoreModule.DB_NAME
import com.heyproject.storyapp.di.CoreModule.TIMEOUT_CONNECTION
import com.heyproject.storyapp.domain.repository.StoryRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private object CoreModule {
    const val BASE_URL = "https://story-api.dicoding.dev/v1/"
    const val TIMEOUT_CONNECTION: Long = 120
    const val DB_NAME = "StoryDB.db"
}

val databaseModule = module {
    factory { get<StoryDatabase>().storyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            StoryDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .build()
    }
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(get())
            .build()
        retrofit.create(StoryApi::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<StoryRepository> { StoryRepositoryImpl(get(), get()) }
}