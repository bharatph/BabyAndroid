package com.thing.baby.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thing.baby.R
import com.thing.baby.model.Message
import kotlinx.android.synthetic.main.message_item.view.*
import java.text.DateFormat
import java.text.DateFormat.getDateInstance

class MessageAdapter(private val context: Context, private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, p0, false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(p0: MessageViewHolder, p1: Int) {
        val message = messages[p1]
        p0.messageTextView.text = message.message
        p0.messageSentTextView.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(message.sentTimestamp)
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.messageTextView
        val messageSentTextView: TextView = itemView.messageSentTextView
    }
}