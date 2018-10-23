package com.thing.baby

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.thing.baby.adapter.MessageAdapter
import com.thing.baby.model.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var messages: ObservableList<Message> = ObservableArrayList()

    fun requestInput(shortHint: String, longHint: String) {
        //messageInputEditText.hint = shortHint
        messages.add(Message("baby", false, longHint, Date()))
    }

    fun output(message: String) {
        messages.add(Message("baby", false, message, Date()))
    }

    fun userInput(message: String) {
        messages.add(Message("sender-uid", false, message, Date(), Random().nextInt(3)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emotionIndicator.emotion = Color.GRAY


        val adapter = MessageAdapter(this, messages)
        messagesRecyclerView.adapter = adapter
        messagesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        class TextWatcherL : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //set drawable
                if (s.isNullOrBlank()) {
                    messageActionImageButton.setImageDrawable(getDrawable(R.drawable.ic_attachment))
                    messageActionImageButton.setOnClickListener {
                        messages.add(Message("sender-uid", true, "url", Date(), Message.READ))
                        output("I cannot read attachments as of now")
                    }
                } else {
                    messageActionImageButton.setImageDrawable(getDrawable(android.R.drawable.ic_menu_send))
                    messageActionImageButton.setOnClickListener {
                        userInput(messageInputEditText.text.toString())
                        messagesRecyclerView.scrollToPosition(adapter.itemCount - 1)
                        s?.clear()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        messageInputEditText.addTextChangedListener(TextWatcherL())
        messageInputEditText.text.clear()
        messageInputEditText.requestFocus()

        messages.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Message>>() {
            override fun onItemRangeRemoved(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<Message>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
            }

            override fun onItemRangeInserted(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeChanged(sender: ObservableList<Message>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onChanged(sender: ObservableList<Message>?) {
            }
        })

        output("Hi, thanks for participating in this experiment")
        output("Tell anything and I will learn")

        register()

    }

    private fun register() {
        output("To identify you across devices, we need you to sign in")
        requestInput("Enter email address", "Enter your email id to proceed")
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
