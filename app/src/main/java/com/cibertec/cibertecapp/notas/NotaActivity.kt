package com.cibertec.cibertecapp.notas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.cibertecapp.R

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NotaActivity: AppCompatActivity(), NoteAdapter.ItemClickListener {

    private lateinit var noteViewModel: NotaViewModel
    lateinit var list: List<Nota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        title = "Gestión de Notas"

        noteViewModel = run {
            ViewModelProvider(this)[NotaViewModel::class.java]
        }

        val recyclerNotes = findViewById<RecyclerView>(R.id.recyclerNotes)

        val adapter = NoteAdapter(this)
        recyclerNotes.adapter = adapter
        recyclerNotes.layoutManager = LinearLayoutManager(this)

        noteViewModel.notes?.observe(this){ notes ->
            if (notes.isNotEmpty()) {
                recyclerNotes.visibility = View.VISIBLE

                list = notes

                notes?.let {
                    adapter.setNotes(it)
                }

            } else {
                recyclerNotes.visibility = View.GONE
            }
        }

    }

    private fun registerAndUpdateNote(note: Nota?, type: TypeOperation) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_nota, null)

        val titleAlertNote = if (type == TypeOperation.REGISTER) "Registrar nota" else "Editar nota"

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(titleAlertNote)

        val mAlertDialog = mBuilder.show()

        val buttonCreate  = mDialogView.findViewById<Button>(R.id.btnGuardarNota)
        val editTextTitleCreate  = mDialogView.findViewById<EditText>(R.id.edtNotaTitulo)
        val editTextDescriptionCreate  = mDialogView.findViewById<EditText>(R.id.edtNotaContenido)

        if (type == TypeOperation.UPDATE){
            editTextTitleCreate.setText(note?.title)
            editTextDescriptionCreate.setText(note?.description)
        }

        buttonCreate.setOnClickListener {

            mAlertDialog.dismiss()

            val titleNote = editTextTitleCreate.text.toString()
            val descriptionNote = editTextDescriptionCreate.text.toString()
            val currentDateTime = LocalDateTime.now().formatChangeNote()

            var noteVM = Nota(titleNote, descriptionNote, currentDateTime)

            if (type == TypeOperation.UPDATE){
                noteVM.noteID = note!!.noteID
                // noteViewModel.updateNoteWithCoroutines(noteVM)
            } else {
                noteViewModel.saveNoteWithCoroutines(noteVM)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nota, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.registerNote -> {
                registerAndUpdateNote(null, TypeOperation.REGISTER)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(noteItem: Nota) {
        registerAndUpdateNote(noteItem, TypeOperation.UPDATE)
    }

}

fun LocalDateTime.formatChangeNote() : String
        = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

enum class TypeOperation {
    REGISTER, UPDATE
}