package com.mis.route.chatapp.data.repos.room_repo

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.model.RoomMessage
import com.mis.route.chatapp.model.UserProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getAllRooms(): List<Room> {
        val collection = FirebaseFirestore.getInstance().collection(Room.COLLECTION_ROOM_NAME)
        val querySnapshot = collection.get().await()
        return querySnapshot.toObjects(Room::class.java)
    }

    override suspend fun sendMessage(message: String, roomId: String) {
        val roomDoc =
            FirebaseFirestore.getInstance().collection(Room.COLLECTION_ROOM_NAME).document(roomId)
        val messageDocRef = roomDoc.collection(RoomMessage.COLLECTION_NAME).document()
        val roomMessage = RoomMessage(
            id = messageDocRef.id,
            senderId = UserProvider.user!!.id!!,
            senderName = UserProvider.user!!.userName!!,
            contentMessage = message,
            date = Timestamp.now()
        )
        messageDocRef.set(roomMessage).await()
    }

    override suspend fun startListenMessagesChanges(roomId: String): Flow<List<RoomMessage>> =
        flow {
            FirebaseFirestore.getInstance().collection(Room.COLLECTION_ROOM_NAME).document(roomId)
                .collection(RoomMessage.COLLECTION_NAME).orderBy("date").snapshots().collect {
                    emit(it.toObjects(RoomMessage::class.java))
                }
        }

    override suspend fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}