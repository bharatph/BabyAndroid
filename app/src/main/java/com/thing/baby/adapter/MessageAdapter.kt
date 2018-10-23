package com.thing.baby.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.thing.baby.R
import com.thing.baby.model.Message
import kotlinx.android.synthetic.main.message_item.view.*
import java.text.DateFormat

class MessageAdapter(private val context: Context, private val messages: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, p0, false)
        return if (viewType == RECEIVED) MessageReceivedViewHolder(view) else MessageSentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.senderUid == "baby") RECEIVED else SENT
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var message = messages[p1]
        if (getItemViewType(p1) == RECEIVED) (p0 as MessageReceivedViewHolder).bind(message)
        else (p0 as MessageSentViewHolder).bind(message)
    }

    class MessageReceivedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.messageTextView
        val messageSentTextView: TextView = itemView.messageSentTextView

        fun bind(message: Message) {
            messageTextView.text = message.message
            messageSentTextView.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(message.sentTimestamp)
        }
    }

    class MessageSentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.messageTextView
        val messageSentTextView: TextView = itemView.messageSentTextView

        fun bind(message: Message) {
            itemView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            (itemView as LinearLayout).gravity = Gravity.START

            messageTextView.background =
                    messageTextView.context.resources.getDrawable(
                        R.drawable.message_me_bg,
                        messageTextView.context.theme
                    )
            messageTextView.text = message.message
            messageSentTextView.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(message.sentTimestamp)
        }
    }

    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }
}