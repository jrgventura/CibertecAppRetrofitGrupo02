package com.cibertec.cibertecapp.login

import com.cibertec.cibertecapp.network.ApiService
import com.cibertec.cibertecapp.network.LoginRequest
import com.cibertec.cibertecapp.network.LoginResponse
import io.reactivex.Single

class LoginRepository {

    private val api = ApiService().apiService

    fun login(email: String, pass :String): Single<LoginResponse> {
        return api.login(LoginRequest(email, pass))
    }

}