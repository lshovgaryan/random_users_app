package com.levon.randomusersproject.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.levon.randomusersproject.R
import com.levon.randomusersproject.callbacks.UsersPageCallbacks
import com.levon.randomusersproject.databinding.ActivityUsersBinding
import com.levon.randomusersproject.factories.UsersViewModelFactory
import com.levon.randomusersproject.viewmodels.UsersViewModel

class UsersActivity : AppCompatActivity(), UsersPageCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityUsersBinding: ActivityUsersBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_users)

        val viewModel: UsersViewModel = ViewModelProviders
            .of(this, UsersViewModelFactory(this))
            .get(UsersViewModel::class.java)

        activityUsersBinding.viewModel = viewModel
    }
}
