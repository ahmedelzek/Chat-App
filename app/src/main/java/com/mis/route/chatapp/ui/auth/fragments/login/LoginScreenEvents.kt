package com.mis.route.chatapp.ui.auth.fragments.login

sealed class LoginScreenEvents {
    data object NavigateToRegisterEvent : LoginScreenEvents()
    data object NavigateToHomeEvent : LoginScreenEvents()
    data object NavigateToResetPasswordEvent : LoginScreenEvents()
}