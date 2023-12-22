package com.example.a2024updates.unused

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024updates.R

class ChartActivity : AppCompatActivity() {

    var values = floatArrayOf(600f,400f)//300f, 400f, 100f, 500f)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


        val linear = findViewById<View>(R.id.linear) as LinearLayout
        values = calculateData(values)
        linear.addView(MyGraphview(this, values))


    }

    private fun calculateData(data: FloatArray): FloatArray {
        // TODO Auto-generated method stub
        var total = 0f
        for (i in data.indices) {
            total += data[i]
        }
        for (i in data.indices) {
            data[i] = 360 * (data[i] / total)
        }
        return data
    }

    class MyGraphview(context: Context?, values: FloatArray) :
        View(context) {
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        private val value_degree: FloatArray
        private val COLORS = intArrayOf(Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN, Color.RED)
        var rectf = RectF(200f, 200f, 800f, 800f)
        var temp = 0

        init {
            value_degree = FloatArray(values.size)
            for (i in values.indices) {
                value_degree[i] = values[i]
            }
        }

        override fun onDraw(canvas: Canvas) {
            // TODO Auto-generated method stub
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

}