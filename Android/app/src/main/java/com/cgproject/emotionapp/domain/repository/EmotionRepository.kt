package com.cgproject.emotionapp.domain.repository

import android.graphics.Bitmap

interface EmotionRepository {

    suspend fun recognizeEmotion(bitmap: Bitmap): String
}