package com.cibertec.cibertecapp.notas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotaDao {

    @Insert
    fun insert(nota: Nota)

    @Update
    fun update(nota: Nota)

    @Delete
    fun delete(nota: Nota)

    @Query("SELECT * FROM " +Nota.TABLE_NAME +" ORDER BY date_note DESC")
    fun list(): LiveData<List<Nota>>

}