package com.levon.randomusersproject.viewmodels

import androidx.lifecycle.ViewModel
import com.levon.randomusersproject.callbacks.UsersPageCallbacks
import com.levon.randomusersproject.data.models.user.UserModel
import com.levon.randomusersproject.utils.RetrofitClient
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UsersViewModel(private val usersPageCallbacks: UsersPageCallbacks) : ViewModel() {

    private val RESULTS: String = "10"

    private var page: String = "1"


    fun doUserDataRequest() {
        RetrofitClient
            .instance
            .usersDataRequest(RESULTS, page)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserModel> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: UserModel) {
                    onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    onFailure(e)
                }

                override fun onComplete() {}
            })

    }

    private fun onSuccess(userData: UserModel) {
        usersPageCallbacks.showNewUsers(userData)
    }

    private fun onFailure(throwable: Throwable) {
        print("Failure -----------")
    }
}