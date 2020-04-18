package com.levon.randomusersproject.viewmodels

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.levon.randomusersproject.callbacks.LoginCallbacks
import com.levon.randomusersproject.data.FirebaseSource
import com.levon.randomusersproject.data.models.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private var loginCallbacks: LoginCallbacks): ViewModel() {
    var loginModel: LoginModel =
        LoginModel(null, null)

    var isSignInStage: ObservableField<Boolean> = ObservableField(true)
    var isLoginButtonEnabled: ObservableField<Boolean> = ObservableField(true)
    var isRegisterButtonEnabled: ObservableField<Boolean> = ObservableField(true)

    private var isPasswordConfirmed: Boolean = false
    private var firebase: FirebaseSource = FirebaseSource()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun emailTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                loginModel.email = s.toString()
            }
        }
    }

    fun passwordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                loginModel.password = s.toString()
            }
        }
    }

    fun confirmPasswordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                isPasswordConfirmed = loginModel.password.equals(s.toString())
            }
        }
    }

    fun onLoginButtonClicked(view: View?) {
        if (isSignInStage.get() == true) {
            isLoginButtonEnabled.set(false)
            loginAction(loginModel.email ?: "", loginModel.password ?: "")
            return
        }

        isRegisterButtonEnabled.set(false)
        if (!isPasswordConfirmed) {
            loginCallbacks.onRegisterFailure("Please Confirm The Password")
            isRegisterButtonEnabled.set(true)
            return
        }

        if (!loginModel.isValidEmail()) {
            loginCallbacks.onRegisterFailure("Your Email Is Not Valid")
            isRegisterButtonEnabled.set(true)
            return
        }

        if (loginModel.isValidPassword()) {
            registerAction(loginModel.email ?: "", loginModel.password ?: "")
        } else {
            loginCallbacks.onRegisterFailure("")
            isRegisterButtonEnabled.set(true)
        }
    }

    fun onFocusChangedFromInput(): OnFocusChangeListener? {
        return loginCallbacks.onFocusChanged()
    }

    fun changeStage(view: View?) {
        isSignInStage.set(!(isSignInStage.get() as Boolean))
    }

    private fun loginAction(email: String, password: String) {
        val disposable = firebase.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isLoginButtonEnabled.set(true)
                    loginCallbacks.onLoginSuccess()
                },
                {
                    isLoginButtonEnabled.set(true)
                    loginCallbacks.onLoginFailure("Can Not Login To Account")
                }
            )
        disposables.add(disposable)
    }

    private fun registerAction(email: String, password: String) {
        val disposable = firebase.register(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isRegisterButtonEnabled.set(true)
                    loginCallbacks.onRegisterSuccess()
                },
                {
                    isRegisterButtonEnabled.set(true)
                    loginCallbacks.onRegisterFailure("Can Not Register Account")
                }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}