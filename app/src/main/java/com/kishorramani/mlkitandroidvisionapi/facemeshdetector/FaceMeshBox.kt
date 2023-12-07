package com.kishorramani.mlkitandroidvisionapi.facemeshdetector

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.google.mlkit.vision.facemesh.FaceMesh
import com.kishorramani.mlkitandroidvisionapi.facedetector.FaceBoxOverlay

class FaceMeshBox(
    overlay: FaceBoxOverlay,
    private val face: FaceMesh,
    private val imageRect: Rect
) : FaceBoxOverlay.FaceBox(overlay) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5.0f
    }

    override fun draw(canvas: Canvas?) {
        val rect = getBoxRect(
            imageRectWidth = imageRect.width().toFloat(),
            imageRectHeight = imageRect.height().toFloat(),
            faceBoundingBox = face.boundingBox
        )
        canvas?.drawRect(rect, paint)
    }
}