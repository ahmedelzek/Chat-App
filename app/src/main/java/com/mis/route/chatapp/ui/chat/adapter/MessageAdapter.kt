package com.mis.route.chatapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.ReceivedMessageBinding
import com.mis.route.chatapp.databinding.SentMessageBinding
import com.mis.route.chatapp.model.RoomMessage
import com.mis.route.chatapp.model.UserProvider

class MessageAdapter(private var messagesList: List<RoomMessage>) :
    Adapter<MessageAdapter.MessageViewHolder>() {

    companion object {
        const val RECEIVED_MESSAGE_VIEW_TYPE = 0
        const val SENT_MESSAGE_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if (viewType == SENT_MESSAGE_VIEW_TYPE) {
            val sentBinding = DataBindingUtil.inflate<SentMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.sent_message,
                parent,
                false
            )
            return MessageViewHolder(sentBinding)
        } else {
            val receivedBinding = DataBindingUtil.inflate<ReceivedMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.received_message,
                parent,
                false
            )
            return MessageViewHolder(receivedBinding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messagesList[position]

        if (message.senderId == UserProvider.user!!.id!!) {
            return SENT_MESSAGE_VIEW_TYPE
        }
        return RECEIVED_MESSAGE_VIEW_TYPE
    }

    fun updateMessagesList(newMessageList: List<RoomMessage>) {
        messagesList = newMessageList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = messagesList.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        holder.bind(messagesList[position])
    }

    class MessageViewHolder(var binding: ViewDataBinding) : ViewHolder(binding.root) {
        fun bind(message: RoomMessage) {
            if (binding is ReceivedMessageBinding) {
                (binding as ReceivedMessageBinding).message = message

            } else {
                (binding as SentMessageBinding).message = message
            }
        }
    }
}

