package com.levon.randomusersproject.callbacks

import android.view.View

interface LoginCallbacks {
    fun onLoginSuccess()
    fun onLoginFailure(message: String)
    fun onRegisterSuccess()
    fun onRegisterFailure(message: String)
    fun onFocusChanged(): View.OnFocusChangeListener
}