package com.cgproject.emotionapp.di

import android.content.Context
import com.cgproject.emotionapp.data.repository.TfLiteEmotionClassifier
import com.cgproject.emotionapp.domain.repository.EmotionClassifier
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
    fun provideEmotionRepository(
        @ApplicationContext context: Context
    ): EmotionClassifier {
        return TfLiteEmotionClassifier(context)
    }
}