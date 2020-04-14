package com.levon.randomusersproject.callbacks

import android.view.View

interface LoginCallbacks {
    fun onLoginSuccess()
    fun onLoginFailure()
    fun onRegisterSuccess()
    fun onRegisterFailure()
    fun onFocusChanged(): View.OnFocusChangeListener
}