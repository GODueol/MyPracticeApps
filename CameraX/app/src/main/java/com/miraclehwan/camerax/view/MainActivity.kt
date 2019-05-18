package com.miraclehwan.camerax.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.databinding.DataBindingUtil
import com.miraclehwan.camerax.R
import com.miraclehwan.camerax.databinding.ActivityMainBinding
import com.miraclehwan.camerax.util.LuminosityAnalyzer
import com.miraclehwan.camerax.util.getFolderRootPath
import com.miraclehwan.camerax.util.hasPermission
import java.io.File
import java.lang.Exception

/**
 *
 * https://codelabs.developers.google.com/codelabs/camerax-getting-started/#0
 */

private const val REQUEST_CODE_PERMISSION = 123;
private val permissionArray = arrayOf(Manifest.permission.CAMERA)
private var lensFacing = CameraX.LensFacing.BACK

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (hasPermission(permissionArray)) {
            binding.textureView.post { startCamera() }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionArray, REQUEST_CODE_PERMISSION)
            }
        }

        binding.textureView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom -> updateTransform() }

        binding.ivChangeCarmera.setOnClickListener{
            lensFacing = if (CameraX.LensFacing.FRONT == lensFacing) {
                CameraX.LensFacing.BACK
            } else {
                CameraX.LensFacing.FRONT
            }
            try {
                CameraX.getCameraWithLensFacing(lensFacing)
                startCamera()
            }catch (execption : Exception){}
        }
    }

    fun startCamera() {

        CameraX.unbindAll()

        //프리뷰
        val previewConfig = PreviewConfig.Builder().apply {
            val metrics = DisplayMetrics().also { binding.textureView.display.getRealMetrics(it) }
            val screenSize = Size(metrics.widthPixels, metrics.heightPixels)
            val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)
            setTargetAspectRatio(screenAspectRatio)
            setTargetResolution(screenSize)
            setLensFacing(lensFacing)
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            //https://stackoverflow.com/questions/56064248/android-camerax-doesnt-show-anything
            val parent = binding.textureView.parent as ViewGroup
            parent.removeView(binding.textureView)
            parent.addView(binding.textureView)

            binding.textureView.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        //이미지 캡쳐
        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            setLensFacing(lensFacing)
        }.build()

        val imageCaptrue = ImageCapture(imageCaptureConfig)
        binding.ivCapture.setOnClickListener {
            val file = File(getFolderRootPath(), "${System.currentTimeMillis()}.jpg")
            imageCaptrue.takePicture(file, object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    val msg = "Photo capture succeeded: ${file.absolutePath}"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d("CameraXApp", msg)
                }

                override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
                    val msg = "Photo capture failed: $message"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.e("CameraXApp", msg)
                    cause?.printStackTrace()
                }

            })
        }

        //이미지분석
        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            val analyzerThread = HandlerThread("LuminosityAnalysis").apply { start() }
            setCallbackHandler(Handler(analyzerThread.looper))
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
            setLensFacing(lensFacing)
        }.build()

        val analyzerUseCase = ImageAnalysis(analyzerConfig).apply { analyzer = LuminosityAnalyzer() }

        CameraX.bindToLifecycle(this, preview, imageCaptrue, analyzerUseCase)
    }

    fun updateTransform() {
        val matrix = Matrix()

        val centerX = binding.textureView.width / 2f
        val centerY = binding.textureView.height / 2f

        val rotationDegrees = when (binding.textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }

        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        binding.textureView.setTransform(matrix)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (hasPermission(permissionArray)) {
                binding.textureView.post { startCamera() }
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
