package com.cgproject.emotionapp.domain.repository

import android.graphics.Bitmap
import com.cgproject.emotionapp.domain.model.Classification

interface EmotionClassifier {

    fun recognizeEmotion(bitmap: Bitmap, rotationDegrees: Int): List<Classification>
}