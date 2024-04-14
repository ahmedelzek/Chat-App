package com.mis.route.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.room_repo.RoomRepoImpl
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private var roomRepo = RoomRepoImpl()
    var events = MutableLiveData<HomeScreenEvents>()

    fun logout() {
        viewModelScope.launch {
            roomRepo.logout()
            events.value = HomeScreenEvents.NavigateToLoginEvent
        }
    }

    fun onAddRoomClicked() {
        events.value = HomeScreenEvents.NavigateToCreateRoomEvent
    }
}