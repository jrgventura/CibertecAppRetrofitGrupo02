package com.cibertec.cibertecapp.maps

import com.google.firebase.firestore.GeoPoint

data class LugarFirestore(
    val titulo: String,
    val posicion: GeoPoint
)
