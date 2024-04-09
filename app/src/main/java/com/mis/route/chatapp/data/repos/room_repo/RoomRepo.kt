package com.mis.route.chatapp.data.repos.room_repo

interface RoomRepo {
    suspend fun createRoom(roomName: String, roomCategory: String, roomDescription: String)
}