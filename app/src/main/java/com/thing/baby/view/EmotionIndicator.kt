package com.thing.baby.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.thing.baby.R

class EmotionIndicator : View {
    val ANGRY = Color.RED
    val CALM = ResourcesCompat.getColor(resources, R.color.neutralSoft, context.theme)
    val FEAR = Color.YELLOW


    var emotion = CALM

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private val emotionPaint = Paint()

    private fun init() {
        emotionPaint.color = emotion
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), width.toFloat() / 2, emotionPaint)
    }
}