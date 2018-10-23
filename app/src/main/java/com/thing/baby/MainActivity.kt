package com.thing.baby

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.thing.baby.adapter.MessageAdapter
import com.thing.baby.model.Message
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.databinding.ObservableMap
import android.databinding.ObservableMap.OnMapChangedCallback
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emotionIndicator.emotion = Color.GRAY
        val messages: ObservableList<Message> = ObservableArrayList()


        val adapter = MessageAdapter(this, messages)
        messagesRecyclerView.adapter = adapter
        messagesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        class TextWatcherL : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //set drawable
                if (s.isNullOrBlank()) {
                    messageActionImageButton.setImageDrawable(getDrawable(R.drawable.ic_attachment))
                    messageActionImageButton.setOnClickListener {
                        messages.add(Message("baby", "I cannot read attachments as of now", Date()))
                    }
                } else {
                    messageActionImageButton.setImageDrawable(getDrawable(android.R.drawable.ic_menu_send))
                    messageActionImageButton.setOnClickListener {
                        messages.add(Message("#2323 2sdf$%$%", s.toString(), Date()))
                        messagesRecyclerView.scrollToPosition(adapter.itemCount - 1)
                        s?.clear()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        messageInputEditText.addTextChangedListener(TextWatcherL())
        messageInputEditText.text = null


        messages.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Message>>() {
            override fun onItemRangeRemoved(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
                adapter.notifyDataSetChanged()
            }

            override fun onItemRangeMoved(
                sender: ObservableList<Message>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
            }

            override fun onItemRangeInserted(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
                adapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
            }

            override fun onChanged(sender: ObservableList<Message>?) {
            }
        })

        messages.add(Message("baby", "Hi, thanks for participating in this experiment", Date()))
        messages.add(Message("baby", "Tell anything and I will learn", Date()))

    }
//
//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    external fun stringFromJNI(): String

    companion object {

//        Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
    }
}
