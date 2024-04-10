package com.mis.route.chatapp.data.repos.room_repo

import com.mis.route.chatapp.model.Room

interface RoomRepo {
    suspend fun createRoom(roomName: String, roomCategory: String, roomDescription: String)
    suspend fun getAllRooms(): List<Room>
}