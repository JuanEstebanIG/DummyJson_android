package com.sena.dummyjson.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providerMoshi(): Moshi{
        return Moshi.Builder().build();
    }

    @Provides
    @Singleton
    fun providerRetrofit(moshi: Moshi) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

}