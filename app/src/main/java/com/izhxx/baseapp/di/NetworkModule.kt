package com.izhxx.baseapp.di

import com.izhxx.baseapp.data.api.RstApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRstApi(): RstApi {
        return RstApi.create()
    }
}