package com.levon.randomusersproject.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.levon.randomusersproject.R
import com.levon.randomusersproject.callbacks.LoginCallbacks
import com.levon.randomusersproject.databinding.ActivityLoginBinding
import com.levon.randomusersproject.factories.LoginViewModelFactory
import com.levon.randomusersproject.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityLoginBinding: ActivityLoginBinding = DataBindingUtil.
            setContentView(this, R.layout.activity_login)//es mek
        var viewModel = ViewModelProviders
            .of(this, LoginViewModelFactory(this))
            .get(LoginViewModel::class.java)//es erku tox
        activityLoginBinding.viewModel = viewModel//es errord tox
    }

    override fun onLoginSuccess() {

    }

    override fun onLoginFailure() {

    }

    override fun onRegisterSuccess() {

    }

    override fun onRegisterFailure() {

    }

    override fun onFocusChanged(): OnFocusChangeListener {
        return OnFocusChangeListener { v, _ ->//6-rd
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager//chorrord
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
