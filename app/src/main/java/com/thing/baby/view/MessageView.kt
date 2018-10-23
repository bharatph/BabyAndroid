package com.thing.baby.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.thing.baby.R
import com.thing.baby.model.Message
import kotlinx.android.synthetic.main.message_view.view.*
import java.text.DateFormat


class MessageView : RelativeLayout {

    lateinit var view: LinearLayout

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        view = LayoutInflater.from(context).inflate(R.layout.message_view, this, false) as LinearLayout
        addView(view)
    }

}