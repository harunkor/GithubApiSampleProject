package com.harunkor.githubapisampleproject.di

import android.content.Context
import com.harunkor.githubapisampleproject.presentation.base.Destination
import com.harunkor.githubapisampleproject.utils.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDestination() = Destination()

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context) = StringProvider(context)

}