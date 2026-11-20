package com.sena.dummyjson.di

import com.sena.dummyjson.data.remote.api.ProductService
import com.sena.dummyjson.data.remote.dto.product.Product
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit) : ProductService{
        return retrofit.create(ProductService::class.java)
    }

}