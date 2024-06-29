package com.cgproject.emotionapp.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cgproject.emotionapp.data.repository.TfLiteEmotionClassifier
import com.cgproject.emotionapp.domain.model.Classification
import com.cgproject.emotionapp.presentation.emotion_screen.CameraPreview
import com.cgproject.emotionapp.presentation.emotion_screen.EmotionImageAnalyzer
import com.cgproject.emotionapp.presentation.emotion_screen.component.ResultText
import com.cgproject.emotionapp.presentation.ui.theme.EmotionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                CAMERAX_PERMISSIONS,
                0
            )
        }
        setContent {
            EmotionAppTheme {
                Surface {
                    var classifications by remember {
                        mutableStateOf(emptyList<Classification>())
                    }
                    val analyzer = remember {
                        EmotionImageAnalyzer(
                            classifier = TfLiteEmotionClassifier(
                                context = applicationContext
                            ),
                            onResults = {
                                classifications = it
                            }
                        )
                    }
                    val controller = remember {
                        LifecycleCameraController(applicationContext).apply {
                            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                            setImageAnalysisAnalyzer(
                                ContextCompat.getMainExecutor(applicationContext),
                                analyzer
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.End)
                                .size(64.dp)
                                .padding(end = 16.dp),
                            onClick = {
                            if(CameraSelector.DEFAULT_BACK_CAMERA == controller.cameraSelector){
                                controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                            }else{
                                controller.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Cameraswitch, contentDescription = "Flip")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        CameraPreview(
                            controller = controller,
                            modifier = Modifier
                                .size(400.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        classifications.forEach { classification ->
                            ResultText(classification = classification)
                        }
                    }
                }
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    }
}