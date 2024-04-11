package com.mis.route.chatapp.ui.chat

import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityChatBinding
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.ui.chat.adapter.MessageAdapter

class ChatActivity : BaseActivity<ChatViewModel, ActivityChatBinding>() {
    lateinit var room: Room
    private lateinit var messageAdapter: MessageAdapter

    companion object {
        const val ROOM_KEY = "room_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        room = if (Build.VERSION.SDK_INT <= TIRAMISU) {
            intent.getSerializableExtra(ROOM_KEY) as Room
        } else {
            intent.getSerializableExtra(ROOM_KEY, Room::class.java)!!
        }
        viewModel.room = room
        viewModel.startListeningMessages()
        initMessagesRecyclerView()
    }

    private fun initMessagesRecyclerView() {
        messageAdapter = MessageAdapter(listOf())
        binding.messagesRv.adapter = messageAdapter
    }

    override fun observeToLiveData() {
        super.observeToLiveData()
        viewModel.messages.observe(this) {
            messageAdapter.updateMessagesList(it)
        }
    }

    override fun initViewModel(): ChatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_chat

}