package com.mis.route.chatapp.model

import java.io.Serializable

data class Room(
    var id: String = "",
    var roomName: String = "",
    var roomCategory: String = "",
    var roomDescription: String = ""
) : Serializable {
    companion object {
        const val COLLECTION_ROOM_NAME = "rooms"
    }
}
