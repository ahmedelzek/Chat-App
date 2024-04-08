package com.mis.route.chatapp.data.repos.auth_repo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
    override suspend fun register(userName: String, email: String, password: String): MyUser {

        val authResult =
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()

        val newUser = MyUser(id = authResult.user!!.uid, userName = userName, email = email)

        val doc = Firebase.firestore.collection(MyUser.COLLECTION_NAME).document(newUser.id!!)

        doc.set(newUser)

        return newUser
    }

    override suspend fun login(email: String, password: String): MyUser {
        Firebase.auth.signInWithEmailAndPassword(email, password)
        throw Exception("\"not implemented yet\"")
    }
}