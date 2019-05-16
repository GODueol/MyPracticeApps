package com.miraclehwan.camerax

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer

class LuminosityAnalyzer : ImageAnalysis.Analyzer {

    private var lastAnalyzedTimestamp = 0L

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        val data = ByteArray(remaining())
        get(data)
        return data
    }

    override fun analyze(image: ImageProxy?, rotationDegrees: Int) {
        val currentTimestamp = System.currentTimeMillis()

        if (image == null){
            return
        }

        if (currentTimestamp - lastAnalyzedTimestamp >= 1000){
            val buffer = image.planes[0].buffer
            val data = buffer.toByteArray()
            val pixels = data.map { it.toInt() and 0xFF }
            val luma = pixels.average()
            Log.d("CameraXApp", "Average luminosity: $luma")
            lastAnalyzedTimestamp = currentTimestamp
        }
    }
}