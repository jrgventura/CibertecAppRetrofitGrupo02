package com.cibertec.cibertecapp.notas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Nota.TABLE_NAME)
data class Nota (
    @ColumnInfo(name = "title_note")
    val title: String,
    @ColumnInfo(name = "description_note")
    val description: String,
    @ColumnInfo(name = "date_note")
    val date: String) {

    companion object { // será static
        const val TABLE_NAME = "note_table"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteID: Int = 0
}