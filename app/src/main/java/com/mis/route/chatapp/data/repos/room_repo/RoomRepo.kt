package com.mis.route.chatapp.data.repos.room_repo

import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import kotlinx.coroutines.flow.Flow

interface RoomRepo {
    suspend fun createRoom(roomName: String, roomCategory: String, roomDescription: String)
    suspend fun getAllRooms(): List<Room>
    suspend fun sendMessage(message: String, roomId: String)
    suspend fun startListenMessagesChanges(roomId: String): Flow<List<RoomMessage>>
    suspend fun logout()
    suspend fun deleteRoom(roomId: String)
}