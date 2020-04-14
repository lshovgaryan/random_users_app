package com.levon.randomusersproject.models

class LoginModel(var username: String?, var password: String?) {

    fun isValidGroupName(): Boolean {
        return username?.length!! > 3
    }

    fun isValidPassword(): Boolean {
        val strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$"
        return password?.matches(Regex(strRegEx)) ?: false
    }
}