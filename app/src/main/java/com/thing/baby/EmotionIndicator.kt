package com.thing.baby

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class EmotionIndicator : View {

    val ANGRY = Color.RED
    val CALM = Color.BLUE
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
        init()
        canvas?.drawCircle(100f, 100f, 100f, emotionPaint)
    }
}