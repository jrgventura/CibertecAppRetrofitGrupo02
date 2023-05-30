package com.cibertec.cibertecapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Arrays

class DialogActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        val btnDialog = findViewById<Button>(R.id.btnOpenDialog)
        btnDialog.setOnClickListener {
            dialogListas()
        }
    }

    fun dialogSimple() {

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setCancelable(true)
            setTitle("Titulo de la alerta")
            setMessage("Mensaje de la alerta")
            setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(context, "Se aceptaron las condiciones",
                Toast.LENGTH_LONG).show()
            }
            setNegativeButton("Cancelar") { dialog, which ->

            }
            setNeutralButton("Talvez") { dialog, which ->

            }
            show()
        }

    }


    fun dialogListas() {

        var items = arrayOf("Java", "Kotlin", "Swift")
        var builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Alerta con lista de datos")
            setItems(items) { _, position ->
                var item = items[position]
                Toast.makeText(context, "Seleccionado: $item",
                    Toast.LENGTH_LONG).show()
            }
            show()
        }

    }


    fun dialogListSimple(){
        var items = arrayOf("Java", "Kotlin", "Swift")
        var itemSeleccionado: Int = -1
        val build = AlertDialog.Builder(this)
        with(build) {
            setTitle("Seleccione un lenguaje Favorito")
            setSingleChoiceItems(items, -1) { dialog, position ->
                itemSeleccionado = position
            }
            setPositiveButton("Aceptar") { _, _ ->
                var item = items[itemSeleccionado]
            }
            show()
        }
    }

    fun dialogSeleccionMultiple() {

        var items = arrayOf("Java", "Kotlin", "Swift")
        var selectedList = ArrayList<Int>()
        var builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Lista Seleccion Mutiple")
            setMultiChoiceItems(items, null) {_, position, ischecked ->
                if (ischecked) {
                    selectedList.add(position)
                } else if (selectedList.contains(position)) {
                    selectedList.remove(Integer.valueOf(position))
                }
            }
            setPositiveButton("Aceptar") { _, _ ->
                var selectedString = ArrayList<String> ()
                for (i in selectedList.indices) {
                    selectedString.add(items[selectedList[i]])
                }
                Toast.makeText(context, "Los elementos seleccionados: " +
                    Arrays.toString(selectedString.toTypedArray()),
                    Toast.LENGTH_SHORT).show()

            }
            show()
        }

    }

    fun dialogCustom() {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Alerta Con Estilo Personalizado")

        val dialogLayout = inflater.inflate(R.layout.dialog_nota, null)
        builder.setView(dialogLayout)

        val button = dialogLayout.findViewById<Button>(R.id.btnGuardarNota)
        val edtTitulo = dialogLayout.findViewById<EditText>(R.id.edtNotaTitulo)
        val edtContenido = dialogLayout.findViewById<EditText>(R.id.edtNotaContenido)

        val mAlertDialog = builder.show()

        button.setOnClickListener {

            val titulo = edtTitulo.text.toString()
            val contenido = edtContenido.text.toString()

            // Guardar en la bd

            mAlertDialog.dismiss()
        }

    }

}








