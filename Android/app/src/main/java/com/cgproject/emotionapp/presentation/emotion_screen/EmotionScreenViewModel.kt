package com.cgproject.emotionapp.presentation.emotion_screen

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgproject.emotionapp.domain.repository.EmotionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmotionScreenViewModel @Inject constructor(
    private val repository: EmotionRepository
) : ViewModel() {

    private val _emotion = MutableStateFlow<String?>(null)
    val emtion: StateFlow<String?> = _emotion

    fun detectEmotion(bitmap: Bitmap) {
        viewModelScope.launch {
            val result = repository.recognizeEmotion(bitmap)
            _emotion.value = result
        }
    }
}