package com.cibertec.cibertecapp.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cibertec.cibertecapp.network.LoginResponse
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel() {

    private val repository = LoginRepository()
    private val composable = CompositeDisposable()
    private lateinit var auth: FirebaseAuth

    val userLoginServiceResponse = MutableLiveData<Boolean>()

    fun login(email: String, pass: String) {
        // loginRetrofit(email, pass)
        loginFirebase(email, pass)
    }

    private fun loginRetrofit(email: String, pass: String) {
        composable.add(
            repository.login(email, pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                    override fun onSuccess(t: LoginResponse) {
                        userLoginServiceResponse.value = true
                    }

                    override fun onError(e: Throwable) {
                        userLoginServiceResponse.value = false
                    }
                })
        )
    }

    private fun loginFirebase(email: String, pass: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) {task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    userLoginServiceResponse.value = true
                } else {
                    userLoginServiceResponse.value = false
                }
            }
    }

}