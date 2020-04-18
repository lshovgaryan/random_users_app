package com.levon.randomusersproject.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levon.randomusersproject.callbacks.UsersPageCallbacks
import com.levon.randomusersproject.viewmodels.UsersViewModel

class UsersViewModelFactory(private val usersPageCallbacks: UsersPageCallbacks): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(usersPageCallbacks) as T
    }
}