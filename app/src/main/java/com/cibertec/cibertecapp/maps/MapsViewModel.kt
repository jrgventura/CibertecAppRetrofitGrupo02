package com.cibertec.cibertecapp.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MapsViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore

    val listPlaceMutable = MutableLiveData<List<LugarFirestore>>()

    fun getPlacesFirestore() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("lugares").get()
            .addOnSuccessListener { documentList ->

                val listLugares = arrayListOf<LugarFirestore>()
                for (document in documentList) {
                    val titulo = document.getString("titulo")
                    val posicion = document.getGeoPoint("posicion")
                    if (titulo != null && posicion != null) {
                        val item = LugarFirestore(titulo, posicion)
                        listLugares.add(item)
                    }
                }
                listPlaceMutable.value = listLugares
            }
    }

}