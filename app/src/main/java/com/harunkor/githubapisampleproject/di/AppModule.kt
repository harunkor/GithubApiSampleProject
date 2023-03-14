package com.harunkor.githubapisampleproject.di

import com.harunkor.githubapisampleproject.presentation.base.Destination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDestination() = Destination()
}