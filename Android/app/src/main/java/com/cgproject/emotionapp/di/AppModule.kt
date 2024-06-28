package com.cgproject.emotionapp.di

import android.content.Context
import com.cgproject.emotionapp.data.repository.EmotionRepositoryImpl
import com.cgproject.emotionapp.domain.repository.EmotionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEmotionRepository(context: Context): EmotionRepository {
        return EmotionRepositoryImpl(context)
    }
}