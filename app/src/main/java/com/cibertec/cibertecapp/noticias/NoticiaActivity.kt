package com.cibertec.cibertecapp.noticias

import android.os.Bundle
import android.os.RecoverySystem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cibertec.cibertecapp.R

class NoticiaActivity: AppCompatActivity() {

    val listaNoticias = listOf(
        Noticia("Noticia 01", "Contenido 01", R.drawable.noticias01),
        Noticia("Noticia 02", "Contenido 02", R.drawable.noticias02),
        Noticia("Noticia 03", "Contenido 03", R.drawable.noticias03)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        val recylerNoticias = findViewById<RecyclerView>(R.id.recyclerNoticias)
        recylerNoticias.apply {
            // layoutManager = LinearLayoutManager(context)
            // layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            // layoutManager = GridLayoutManager(context, 2)
            layoutManager = StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL)
            adapter = NoticiaAdapter(listaNoticias)
        }

    }

}