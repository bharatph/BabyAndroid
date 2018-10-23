package com.thing.baby.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.thing.baby.R
import com.thing.baby.model.Message
import com.thing.baby.view.MessageView
import kotlinx.android.synthetic.main.message_view.view.*
import java.text.DateFormat


class MessageAdapter(private val context: Context, private val messages: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, p0, false) as MessageView
        return if (viewType == RECEIVED) MessageReceivedViewHolder(view) else MessageSentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.senderUid == "baby") RECEIVED else SENT
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var message = messages[p0.adapterPosition]
        if (getItemViewType(p0.adapterPosition) == RECEIVED) (p0 as MessageReceivedViewHolder).bind(message)
        else (p0 as MessageSentViewHolder).bind(message)
    }


    open class MessageViewHolder(itemView: MessageView) : RecyclerView.ViewHolder(itemView) {
        var view: LinearLayout = itemView.view
        var messageContent: LinearLayout = view.messageContent
        var messageSentTextView: TextView = view.messageSentTextView

        open fun bind(message: Message) {
            if (message.isMedia) {
                val imageView = ImageView(itemView.context, null)
                imageView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_attachment))
                messageContent.addView(imageView)
            } else {
                val textView = LayoutInflater.from(itemView.context).inflate(
                    R.layout.content_text,
                    messageContent,
                    false
                ) as TextView
                textView.text = message.message
                messageContent.addView(textView)
            }
            view.messageSentTextView.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(message.sentTimestamp)
            setIsRecyclable(false)
        }
    }

    class MessageReceivedViewHolder(itemView: MessageView) : MessageViewHolder(itemView) {
        override fun bind(message: Message) {
            super.bind(message)
        }
    }

    class MessageSentViewHolder(itemView: MessageView) : MessageViewHolder(itemView) {
        override fun bind(message: Message) {
            super.bind(message)
            messageContent.background = when (message.receipt) {
                Message.RECEIVED -> itemView.context.getDrawable(R.drawable.message_received_bg)
                Message.READ -> itemView.context.getDrawable(R.drawable.message_read_bg)
                else -> itemView.context.getDrawable(R.drawable.message_sent_bg)
            }

            view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            view.gravity = Gravity.START
        }
    }

    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }
}
