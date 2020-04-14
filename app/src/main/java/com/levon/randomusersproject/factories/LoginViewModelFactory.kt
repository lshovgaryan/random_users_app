package com.levon.randomusersproject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levon.randomusersproject.callbacks.LoginCallbacks
import com.levon.randomusersproject.viewmodels.LoginViewModel

class LoginViewModelFactory(private val loginCallbacks: LoginCallbacks): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginCallbacks) as T
    }
}