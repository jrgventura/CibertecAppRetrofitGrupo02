package com.cibertec.cibertecapp.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CibertecApi {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest) : Single<LoginResponse>

    // https://reqres.in/api/users?page=2&limit=20
    @GET("users")
    fun listarUsuarios(@Query("page") page: Int, @Query("limit") limit: Int)

    // https://reqres.in/api/users/2/100
    @GET("users/{id}/{limit}")
    fun getUser(@Path("id") id: Int, @Path("limit") limit: Int)
}