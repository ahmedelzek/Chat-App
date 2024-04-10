package com.mis.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.room_repo.RoomRepoImpl
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class MyRoomsViewModel : BaseViewModel() {
    var roomsLiveData = MutableLiveData<List<Room>>()
    private var repo = RoomRepoImpl()

    fun refreshRooms() {
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                roomsLiveData.value = repo.getAllRooms()
                isLoadingLiveData.value = false

            } catch (e: Throwable) {
                viewMessageLiveData.value =
                    ViewMessage(title = "Error", message = e.localizedMessage)
            }

        }
    }
}