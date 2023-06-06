package com.cibertec.cibertecapp.registro

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
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
                    if (userId != null){
                        registrarFirestore(userId, email)
                    }
                } else {
                    userRegisterFirebase.value = false
                }
            }
    }
    fun registrarFirestore(uid: String, correo: String) {
        firestore = FirebaseFirestore.getInstance()
        val usuario = UsuarioFirestore(correo)
        firestore.collection("usuarios").document(uid).set(usuario)
            .addOnCompleteListener(Activity()) { task ->
                userRegisterFirebase.value = task.isSuccessful
            }
    }

}