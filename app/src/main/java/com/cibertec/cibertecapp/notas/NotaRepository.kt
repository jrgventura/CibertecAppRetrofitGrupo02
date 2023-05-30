package com.cibertec.cibertecapp.notas

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.cibertecapp.CibertecDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotaRepository (application: Application) {

    private val noteDao: NotaDao? =
        CibertecDatabase.getInstance(application)?.notaDao()

    suspend fun insertNoteWithCoroutines(note: Nota) {
        processInsertNote(note)
    }

    private suspend fun processInsertNote(note: Nota) {
        withContext(Dispatchers.Default) {
            noteDao?.insert(note)
        }
    }

    fun getNotes(): LiveData<List<Nota>>? {
        return noteDao?.list()
    }

}