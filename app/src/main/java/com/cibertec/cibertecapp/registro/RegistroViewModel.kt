package com.cibertec.cibertecapp.registro

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistroViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth
    val userRegisterFirebase = MutableLiveData<Boolean>()

    fun registrar(email: String, pass: String) {
        registrarFirebase(email, pass)
    }

    private fun registrarFirebase(email: String, pass: String){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    userRegisterFirebase.value = true
                } else {
                    userRegisterFirebase.value = false
                }
            }
    }

}