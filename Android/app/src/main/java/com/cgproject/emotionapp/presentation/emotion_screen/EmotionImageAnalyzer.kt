package com.cgproject.emotionapp.presentation.emotion_screen

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.cgproject.emotionapp.domain.model.Classification
import com.cgproject.emotionapp.domain.repository.EmotionClassifier

class EmotionImageAnalyzer(
    private val classifier: EmotionClassifier,
    private val onResults: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap().centerCrop(224, 224)
            val results = classifier.recognizeEmotion(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++
        image.close()
    }


}