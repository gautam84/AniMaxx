package com.example.animaxx.di

import android.content.Context
import com.example.animaxx.data.repository.MainRepositoryImpl
import com.example.animaxx.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserDataRepository(
        @ApplicationContext context: Context

    ): MainRepository = MainRepositoryImpl(context)


}