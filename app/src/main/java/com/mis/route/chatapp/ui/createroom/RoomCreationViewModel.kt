package com.mis.route.chatapp.ui.createroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.room_repo.RoomRepo
import com.mis.route.chatapp.data.repos.room_repo.RoomRepoImpl
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class RoomCreationViewModel : BaseViewModel() {

    var roomNameLiveData = MutableLiveData("")
    var roomCategoryLiveData = MutableLiveData("")
    var roomDescriptionLiveData = MutableLiveData("")
    var roomNameErrorLiveData = MutableLiveData<String?>()
    var roomCategoryErrorLiveData = MutableLiveData<String?>()
    var roomDescriptionErrorLiveData = MutableLiveData<String?>()
    private var roomRepo: RoomRepo = RoomRepoImpl()
    var events = MutableLiveData<RoomCreationEvents>()

    fun createRoom() {
        if (!validate()) return
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                roomRepo.createRoom(
                    roomNameLiveData.value!!,
                    roomCategoryLiveData.value!!,
                    roomDescriptionLiveData.value!!
                )
                isLoadingLiveData.value = false
                events.value = RoomCreationEvents.RoomCreated
            } catch (e: Throwable) {
                isLoadingLiveData.value = false
                viewMessageLiveData.value =
                    ViewMessage(title = "Error", message = e.localizedMessage)
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (roomNameLiveData.value.isNullOrEmpty()) {
            roomNameErrorLiveData.value = "Please Enter Valid Room Name..!"
            isValid = false
        } else {
            roomNameErrorLiveData.value = null
        }
        if (roomCategoryLiveData.value.isNullOrEmpty()) {
            roomCategoryErrorLiveData.value = "Please Enter Valid Room Category..!"
            isValid = false
        } else {
            roomCategoryErrorLiveData.value = null
        }
        if (roomDescriptionLiveData.value.isNullOrEmpty()) {
            roomDescriptionErrorLiveData.value = "Please Enter Valid Room Description..!"
            isValid = false
        } else {
            roomDescriptionErrorLiveData.value = null
        }
        return isValid
    }
}