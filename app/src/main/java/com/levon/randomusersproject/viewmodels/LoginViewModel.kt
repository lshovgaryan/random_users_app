package com.levon.randomusersproject.viewmodels

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.levon.randomusersproject.callbacks.LoginCallbacks
import com.levon.randomusersproject.data.models.LoginModel

class LoginViewModel(private var loginCallbacks: LoginCallbacks): ViewModel() {
    var loginModel: LoginModel =
        LoginModel(null, null)

    var isSignInStage: ObservableField<Boolean> = ObservableField(true)

    fun groupNameTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                loginModel.username = s.toString()
            }
        }
    }

    fun passwordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                loginModel.password = s.toString()
            }
        }
    }

    fun onLoginButtonClicked(view: View?) {
        if (!loginModel.isValidGroupName()) {
            loginCallbacks.onLoginFailure()
            return
        }
        if (loginModel.isValidPassword()) {
            loginCallbacks.onLoginSuccess()
        } else {
            loginCallbacks.onLoginFailure()
        }
    }

    fun onFocusChangedFromInput(): OnFocusChangeListener? {
        return loginCallbacks.onFocusChanged()
    }

    fun changeStage(view: View?) {
        isSignInStage.set(!(isSignInStage.get() as Boolean))
    }
}