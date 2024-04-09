package com.mis.route.chatapp.ui.createroom

sealed class RoomCreationEvents {

    data object RoomCreated : RoomCreationEvents()
}