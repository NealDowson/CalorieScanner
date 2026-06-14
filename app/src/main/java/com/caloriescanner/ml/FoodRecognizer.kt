package com.caloriescanner.ml

import android.content.Context
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class RecognitionResult(
    val label: String,
    val confidence: Float,
    val index: Int
)

class FoodRecognizer(private val context: Context) {

    private val labelerOptions = ImageLabelerOptions.Builder()
        .setConfidenceThreshold(0.5f)
        .build()

    private val labeler = ImageLabeling.getClient(labelerOptions)

    suspend fun recognizeFood(bitmap: Bitmap): List<RecognitionResult> {
        val image = InputImage.fromBitmap(bitmap, 0)

        return suspendCancellableCoroutine { continuation ->
            labeler.process(image)
                .addOnSuccessListener { labels ->
                    val results = labels.map { label ->
                        RecognitionResult(
                            label = label.text,
                            confidence = label.confidence,
                            index = label.index
                        )
                    }
                    continuation.resume(results)
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
        }
    }

    fun mapLabelToFoodCategory(label: String): String {
        val lowerLabel = label.lowercase()
        return when {
            lowerLabel.contains("apple") || lowerLabel.contains("fruit") -> "Фрукты"
            lowerLabel.contains("banana") || lowerLabel.contains("orange") -> "Фрукты"
            lowerLabel.contains("vegetable") || lowerLabel.contains("carrot") -> "Овощи"
            lowerLabel.contains("tomato") || lowerLabel.contains("potato") -> "Овощи"
            lowerLabel.contains("meat") || lowerLabel.contains("chicken") -> "Мясо"
            lowerLabel.contains("beef") || lowerLabel.contains("pork") -> "Мясо"
            lowerLabel.contains("fish") || lowerLabel.contains("salmon") -> "Рыба"
            lowerLabel.contains("dairy") || lowerLabel.contains("milk") -> "Молочные"
            lowerLabel.contains("cheese") || lowerLabel.contains("egg") -> "Молочные"
            lowerLabel.contains("bread") || lowerLabel.contains("rice") -> "Зерновые"
            lowerLabel.contains("pasta") || lowerLabel.contains("noodle") -> "Зерновые"
            lowerLabel.contains("nut") || lowerLabel.contains("almond") -> "Орехи"
            lowerLabel.contains("drink") || lowerLabel.contains("coffee") -> "Напитки"
            lowerLabel.contains("tea") || lowerLabel.contains("juice") -> "Напитки"
            lowerLabel.contains("cake") || lowerLabel.contains("cookie") -> "Сладости"
            lowerLabel.contains("chocolate") || lowerLabel.contains("candy") -> "Сладости"
            lowerLabel.contains("pizza") || lowerLabel.contains("burger") -> "Фастфуд"
            lowerLabel.contains("fries") || lowerLabel.contains("fast") -> "Фастфуд"
            lowerLabel.contains("soup") || lowerLabel.contains("stew") -> "Супы"
            else -> "Другое"
        }
    }

    fun onDestroy() {
        labeler.close()
    }
}
