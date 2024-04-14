package com.mis.route.chatapp.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.room_repo.RoomRepoImpl
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    lateinit var room: Room
    var messageLiveData = MutableLiveData("")
    private var roomRepo = RoomRepoImpl()
    var messages = MutableLiveData<List<RoomMessage>>()

    fun sendMessage() {
        if (!validate()) return
        viewModelScope.launch {
            try {
                roomRepo.sendMessage(messageLiveData.value!!, room.id)
                messageLiveData.value = ""
            } catch (t: Throwable) {
                viewMessageLiveData.value = ViewMessage("Error", t.localizedMessage)
            }
        }
    }

    fun startListeningMessages() {
        viewModelScope.launch {
            try {
                roomRepo.startListenMessagesChanges(room.id).collect {
                    messages.value = it
                }
            } catch (t: Throwable) {
                viewMessageLiveData.value = ViewMessage("Error", t.localizedMessage)
            }
        }
    }


    private fun validate(): Boolean {
        var isValid = true
        if (messageLiveData.value.isNullOrEmpty()) {
            isValid = false
        }
        return isValid
    }
}