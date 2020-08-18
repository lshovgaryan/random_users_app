package com.levon.randomusersproject.utils

import com.levon.randomusersproject.data.models.user.UserModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("/api")
    fun usersDataRequest(
        @Query("results") results: String,
        @Query("page") page: String): Flowable<UserModel>
}