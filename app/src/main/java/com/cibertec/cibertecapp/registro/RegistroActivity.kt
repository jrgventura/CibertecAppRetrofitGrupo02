package com.cibertec.cibertecapp.registro

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.cibertecapp.R
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity: AppCompatActivity() {

    private lateinit var viewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this)[RegistroViewModel::class.java]

        val edtEmail = findViewById<TextInputEditText>(R.id.edtEmail)
        val edtPassword = findViewById<TextInputEditText>(R.id.edtPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            viewModel.registrar(email, pass)
        }
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.userRegisterFirebase.observe(this) {
            if (it) {
                Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}