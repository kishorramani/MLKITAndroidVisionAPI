package com.kishorramani.mlkitandroidvisionapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.kishorramani.mlkitandroidvisionapi.databinding.ActivityMainBinding
import com.kishorramani.mlkitandroidvisionapi.facedetector.FaceDetectionActivity
import com.kishorramani.mlkitandroidvisionapi.facemeshdetector.FaceMeshDetectionActivity
import com.kishorramani.mlkitandroidvisionapi.qrscanner.ScannerActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val cameraPermission = android.Manifest.permission.CAMERA
    private var action = Action.QR_SCANNER

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonOpenScanner.setOnClickListener {
            action = Action.QR_SCANNER
            requestCameraAndStart()
        }

        binding.buttonFaceDetect.setOnClickListener {
            action = Action.FACE_DETECTION
            requestCameraAndStart()
        }

        binding.buttonFaceMeshDetect.setOnClickListener {
            action = Action.FACE_MESH_DETECTION
            requestCameraAndStart()
        }
    }

    private fun requestCameraAndStart() {
        if (isPermissionGranted(cameraPermission)) {
            startCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        when {
            shouldShowRequestPermissionRationale(cameraPermission) -> {
                cameraPermissionRequest(positive = {
                    openPermissionSetting()
                })
            }

            else -> {
                requestPermissionLauncher.launch(cameraPermission)
            }
        }
    }

    private fun startCamera() {
        when (action) {
            Action.QR_SCANNER -> startScanner()
            Action.FACE_DETECTION -> startFaceDetection()
            Action.FACE_MESH_DETECTION -> startFaceMeshDetection()
        }
    }

    private fun startFaceMeshDetection() {
        FaceMeshDetectionActivity.startActivity(this)
    }

    private fun startScanner() {
        ScannerActivity.startScanner(this)
    }

    private fun startFaceDetection() {
        FaceDetectionActivity.startActivity(this)
    }
}