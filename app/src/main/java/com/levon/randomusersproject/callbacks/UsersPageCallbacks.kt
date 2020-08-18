package com.levon.randomusersproject.callbacks

import com.levon.randomusersproject.data.models.user.UserModel

interface UsersPageCallbacks {
    fun showNewUsers(userModel: UserModel)
}