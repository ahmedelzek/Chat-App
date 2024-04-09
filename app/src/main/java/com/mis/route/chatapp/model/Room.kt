package com.mis.route.chatapp.model

data class Room(
    var id: String = "",
    var roomName: String = "",
    var roomCategory: String = "",
    var roomDescription: String = ""
) {
    companion object {
        const val COLLECTION_ROOM_NAME = "rooms"
    }
}
