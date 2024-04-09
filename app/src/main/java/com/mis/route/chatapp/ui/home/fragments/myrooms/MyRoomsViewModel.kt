package com.mis.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class MyRoomsViewModel : BaseViewModel() {
    var rooms = MutableLiveData<List<Room>>()

    fun refreshRooms() {
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true

                isLoadingLiveData.value = false

            } catch (e: Throwable) {
                viewMessageLiveData.value =
                    ViewMessage(title = "Error", message = e.localizedMessage)
            }

        }
    }
}