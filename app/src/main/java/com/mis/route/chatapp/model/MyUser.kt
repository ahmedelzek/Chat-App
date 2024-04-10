package com.mis.route.chatapp.model

data class MyUser(val id: String? = null, val userName: String? = null, val email: String? = null) {

    companion object {
        const val COLLECTION_NAME = "users"
    }
}
