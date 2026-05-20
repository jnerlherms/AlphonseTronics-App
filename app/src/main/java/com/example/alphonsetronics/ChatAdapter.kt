package com.example.alphonsetronics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layoutSent: View = itemView.findViewById(R.id.layoutSent)
        val layoutReceived: View = itemView.findViewById(R.id.layoutReceived)
        val tvSentMessage: TextView = itemView.findViewById(R.id.tvSentMessage)
        val tvSentTime: TextView = itemView.findViewById(R.id.tvSentTime)
        val tvReceivedMessage: TextView = itemView.findViewById(R.id.tvReceivedMessage)
        val tvReceivedTime: TextView = itemView.findViewById(R.id.tvReceivedTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        if (message.isSentByUser) {
            holder.layoutSent.visibility = View.VISIBLE
            holder.layoutReceived.visibility = View.GONE
            holder.tvSentMessage.text = message.text
            holder.tvSentTime.text = message.time
        } else {
            holder.layoutSent.visibility = View.GONE
            holder.layoutReceived.visibility = View.VISIBLE
            holder.tvReceivedMessage.text = message.text
            holder.tvReceivedTime.text = message.time
        }
    }

    override fun getItemCount() = messages.size
}