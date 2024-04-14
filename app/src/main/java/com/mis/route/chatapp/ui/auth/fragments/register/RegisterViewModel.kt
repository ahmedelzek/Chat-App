package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.auth_repo.AuthRepo
import com.mis.route.chatapp.data.repos.auth_repo.AuthRepoImpl
import com.mis.route.chatapp.model.UserProvider
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    private var authRepo: AuthRepo = AuthRepoImpl()
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var userNameLiveData = MutableLiveData("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    var userNameErrorLiveData = MutableLiveData<String?>()
    var events = MutableLiveData<RegisterScreenEvents>()

    fun register() {
        if (!validate()) return
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val user = authRepo.register(
                    userNameLiveData.value!!,
                    emailLiveData.value!!,
                    passwordLiveData.value!!
                )
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = RegisterScreenEvents.NavigateToHomeEvent
            } catch (e: Exception) {
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage
                )
                isLoadingLiveData.value = false
            }
        }
    }

    fun goToLogin() {
        events.value = RegisterScreenEvents.NavigateToLoginEvent
    }

    private fun validate(): Boolean {
        var isValid = true
        if (userNameLiveData.value.isNullOrEmpty()) {
            userNameErrorLiveData.value = "Please Enter Valid UserName..!"
            isValid = false
        } else {
            userNameErrorLiveData.value = null
        }
        if (emailLiveData.value.isNullOrEmpty()) {
            emailErrorLiveData.value = "Please Enter Valid Email..!"
            isValid = false
        } else {
            emailErrorLiveData.value = null
        }
        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordErrorLiveData.value = "Please Enter Valid Password..!"
            isValid = false
        } else {
            passwordErrorLiveData.value = null
        }
        return isValid
    }
}