package com.caloriescanner

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.caloriescanner.ml.FoodRecognizer
import com.caloriescanner.ml.RecognitionResult
import com.caloriescanner.ui.FoodAnalysisScreen
import com.caloriescanner.ui.HomeScreen
import com.caloriescanner.ui.theme.CalorieScannerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var foodRecognizer: FoodRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        foodRecognizer = FoodRecognizer(this)

        setContent {
            CalorieScannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalorieScannerApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        foodRecognizer.onDestroy()
    }

    @Composable
    fun CalorieScannerApp() {
        var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
        var recognitionResults by remember { mutableStateOf<List<RecognitionResult>>(emptyList()) }
        var isAnalyzing by remember { mutableStateOf(false) }
        var showResults by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        val coroutineScope = rememberCoroutineScope()

        when {
            showResults && selectedBitmap != null -> {
                FoodAnalysisScreen(
                    bitmap = selectedBitmap,
                    recognitionResults = recognitionResults,
                    onBack = {
                        showResults = false
                        selectedBitmap = null
                        recognitionResults = emptyList()
                        errorMessage = null
                    }
                )
            }
            isAnalyzing -> {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        androidx.compose.foundation.layout.Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            androidx.compose.material3.CircularProgressIndicator(
                                modifier = Modifier.size(64.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            androidx.compose.foundation.layout.Spacer(
                                modifier = Modifier.height(16.dp)
                            )
                            androidx.compose.material3.Text(
                                text = "Анализируем изображение...",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
            else -> {
                HomeScreen(
                    onImageSelected = { bitmap ->
                        selectedBitmap = bitmap
                        isAnalyzing = true
                        errorMessage = null

                        coroutineScope.launch {
                            try {
                                val results = foodRecognizer.recognizeFood(bitmap)
                                recognitionResults = results
                                showResults = true
                            } catch (e: Exception) {
                                errorMessage = "Ошибка анализа: ${e.message}"
                                showResults = true
                            } finally {
                                isAnalyzing = false
                            }
                        }
                    }
                )
            }
        }
    }
}
