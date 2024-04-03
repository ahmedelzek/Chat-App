package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var emailLiveData = MutableLiveData<String>("")
    var passwordLiveData = MutableLiveData<String>("")
    var userNameLiveData = MutableLiveData<String>("")
}