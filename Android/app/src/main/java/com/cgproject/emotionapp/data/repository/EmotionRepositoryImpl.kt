package com.cgproject.emotionapp.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.cgproject.emotionapp.domain.repository.EmotionRepository
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class EmotionRepositoryImpl(
    context: Context
) : EmotionRepository {

    private val interpreter: Interpreter

    init {
        val model = loadModelFile(context, "model.tflite")
        interpreter = Interpreter(model)
    }

    private fun loadModelFile(
        context: Context,
        modelName: String
    ): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    override suspend fun recognizeEmotion(bitmap: Bitmap): String {
        val tensorImage = TensorImage.fromBitmap(bitmap)
        val inputBuffer = tensorImage.tensorBuffer

        val outputBuffer = TensorBuffer
            .createFixedSize(intArrayOf(1, 7), tensorImage.dataType)

        interpreter.run(inputBuffer.buffer, outputBuffer.buffer.rewind())

        val emotions = arrayOf(
            "Surprise",
            "Sad",
            "Neutral",
            "Happy",
            "Fear",
            "Disgust",
            "Angry"
        )

        val emotionProbabilities = outputBuffer.floatArray
        val maxIndex = emotionProbabilities.indices.maxByOrNull {
            emotionProbabilities[it]
        } ?: -1

        return emotions[maxIndex]
    }
}