package com.mis.route.chatapp.model

data class MyUser(val id: String?, val userName: String?, val email: String?) {

    companion object {
        val COLLECTION_NAME = "users"
    }
}
