package com.example.room_demo.ui.components.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.room_demo.databinding.CameraPreviewBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: CameraPreviewBinding
    private var cameraProvider: ProcessCameraProvider? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CameraPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()
    }
    override fun onResume() {
        super.onResume()
        startCamera()
        binding.clickButton.setOnClickListener {
            captureImage()
        }
    }

    override fun onPause() {
        super.onPause()
        cameraProvider?.unbindAll()
    }

    private fun getCameraProvider(): ProcessCameraProvider {
        if (cameraProvider == null) {
            cameraProvider = ProcessCameraProvider.getInstance(this).get()
        }
        return cameraProvider!!
    }
    private fun startCamera() {
        cameraProvider = getCameraProvider()
        imageCapture = ImageCapture.Builder()
            .build()

        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            }
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        try {
            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(
                this, cameraSelector, preview, imageCapture
            )
        } catch (exc: Exception) {
            println(exc.message)
        }
    }
    
    private fun captureImage() {
        imageCapture.targetRotation = binding.root.display.rotation
        imageCapture.takePicture(cameraExecutor, object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                println("Success")
            }
            override fun onError(exc: ImageCaptureException) {
                println(exc.message)
            }
        })
    }
}

