package com.levon.randomusersproject.data.models

class LoginModel(var email: String?, var password: String?) {

    fun isValidEmail(): Boolean {
        return (email ?: "").length > 5
    }

    fun isValidPassword(): Boolean {
        val strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$"
        return password?.matches(Regex(strRegEx)) ?: false
    }
}