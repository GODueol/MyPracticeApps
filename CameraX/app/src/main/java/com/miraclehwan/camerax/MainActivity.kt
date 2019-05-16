package com.miraclehwan.camerax

import android.Manifest
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.camera2.impl.PreviewConfigProvider
import androidx.camera.core.*
import androidx.databinding.DataBindingUtil
import com.miraclehwan.camerax.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

/**
 *
 * https://codelabs.developers.google.com/codelabs/camerax-getting-started/#0
 */
class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION = 123;
    private val permissionArray = arrayOf(Manifest.permission.CAMERA)

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (hasPermission(permissionArray)){
            startCamera()
        }else{
            requestMyPermission(REQUEST_CODE_PERMISSION, permissionArray)
        }

        binding.textureView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            updateTransform()
        }
    }

    fun startCamera(){
        //프리뷰
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1,1))
            setTargetResolution(Size(640, 640))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = binding.textureView.parent as ViewGroup
            parent.removeView(binding.textureView)
            parent.addView(binding.textureView)

            binding.textureView.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        //이미지 캡쳐
        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(Rational(1,1))
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
        }.build()

        val imageCaptrue = ImageCapture(imageCaptureConfig)
        binding.btnCapture.setOnClickListener{
            val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
            imageCaptrue.takePicture(file, object: ImageCapture.OnImageSavedListener{
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
        }.build()

        val analyzerUseCase = ImageAnalysis(analyzerConfig).apply {
            analyzer = LuminosityAnalyzer()
        }
        CameraX.bindToLifecycle(this, preview, imageCaptrue, analyzerUseCase)
    }

    fun updateTransform(){
        val matrix = Matrix()

        val centerX = binding.textureView.width / 2f
        val centerY = binding.textureView.height / 2f

        val rotationDegrees = when(binding.textureView.display.rotation){
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
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
