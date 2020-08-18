package com.levon.randomusersproject.ui.activities

import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levon.randomusersproject.R
import com.levon.randomusersproject.callbacks.UsersPageCallbacks
import com.levon.randomusersproject.data.models.user.UserModel
import com.levon.randomusersproject.databinding.ActivityUsersBinding
import com.levon.randomusersproject.factories.UsersViewModelFactory
import com.levon.randomusersproject.ui.adapters.UsersAdapter
import com.levon.randomusersproject.viewmodels.UsersViewModel

class UsersActivity : AppCompatActivity(), UsersPageCallbacks {

    private var usersAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityUsersBinding: ActivityUsersBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_users)

        val viewModel: UsersViewModel = ViewModelProviders
            .of(this, UsersViewModelFactory(this))
            .get(UsersViewModel::class.java)

        activityUsersBinding.viewModel = viewModel

        prepareRecyclerView()
        viewModel.doUserDataRequest()
    }

    override fun showNewUsers(userModel: UserModel) {
        usersAdapter?.addNewUsers(userModel)
    }

    private fun prepareRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.users_recycler_view)
        usersAdapter = UsersAdapter(this)
        recyclerView.adapter = usersAdapter
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
            false)
    }
}
