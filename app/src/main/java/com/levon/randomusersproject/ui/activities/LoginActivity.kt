package com.levon.randomusersproject.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
            setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProviders
            .of(this, LoginViewModelFactory(this))
            .get(LoginViewModel::class.java)
        activityLoginBinding.viewModel = viewModel
    }

    override fun onLoginSuccess() {
        startActivity(Intent(this, UsersActivity::class.java))
    }

    override fun onLoginFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterSuccess() {
        Toast.makeText(this, "Account Registered Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFocusChanged(): OnFocusChangeListener {
        return OnFocusChangeListener { v, _ ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
