package com.mis.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.repos.auth_repo.AuthRepo
import com.mis.route.chatapp.data.repos.auth_repo.AuthRepoImpl
import com.mis.route.chatapp.model.UserProvider
import com.mis.route.chatapp.model.ViewMessage
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    private var authRepo: AuthRepo = AuthRepoImpl()
    var events = MutableLiveData<LoginScreenEvents>()


    fun login() {
        if (!validate()) return
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val user = authRepo.login(
                    emailLiveData.value!!,
                    passwordLiveData.value!!
                )
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = LoginScreenEvents.NavigateToHomeEvent
            } catch (e: Exception) {
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage
                )
                isLoadingLiveData.value = false
            }
        }
    }

    fun createOnClick() {
        events.value = LoginScreenEvents.NavigateToRegisterEvent
    }

    private fun validate(): Boolean {
        var isValid = true
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