package com.mis.route.chatapp.ui.home

sealed class HomeScreenEvents {
    data object NavigateToLoginEvent : HomeScreenEvents()
    data object NavigateToCreateRoomEvent : HomeScreenEvents()

}