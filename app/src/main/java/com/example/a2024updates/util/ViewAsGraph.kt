package com.example.a2024updates.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class ViewAsGraph(context: Context?, values: FloatArray) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val value_degree: FloatArray
    private val COLORS = intArrayOf(Color.BLACK, Color.LTGRAY, Color.GRAY, Color.CYAN, Color.RED)
    var rectf = RectF(100f, 100f, 900f, 900f)
    var temp = 0

    init {
        value_degree = FloatArray(values.size)
        for (i in values.indices) {
            value_degree[i] = values[i]
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in value_degree.indices) { //values2.length; i++) {
            if (i == 0) {
                paint.color = COLORS[i]
                canvas.drawArc(rectf, 0f, value_degree[i], true, paint)
            } else {
                temp += value_degree[i - 1].toInt()
                paint.color = COLORS[i]
                canvas.drawArc(rectf, temp.toFloat(), value_degree[i], true, paint)
            }
        }
    }
}
