package com.mis.route.chatapp.data.repos.room_repo

import com.google.firebase.firestore.FirebaseFirestore
import com.mis.route.chatapp.model.Room
import kotlinx.coroutines.tasks.await

class RoomRepoImpl : RoomRepo {
    override suspend fun createRoom(
        roomName: String,
        roomCategory: String,
        roomDescription: String
    ) {
        val docRef =
            FirebaseFirestore.getInstance().collection(Room.COLLECTION_ROOM_NAME).document()
        val room = Room(docRef.id, roomName, roomCategory, roomDescription)
        docRef.set(room).await()
    }
}