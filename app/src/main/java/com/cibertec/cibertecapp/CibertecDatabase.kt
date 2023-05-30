package com.cibertec.cibertecapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.cibertecapp.notas.Nota
import com.cibertec.cibertecapp.notas.NotaDao

@Database(entities = [Nota::class], version = 1)
abstract class CibertecDatabase: RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {
        private const val DATABASE_NAME = "cibertec_database"

        @Volatile
        private var INSTANCE: CibertecDatabase? = null

        fun getInstance(context: Context): CibertecDatabase? {
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                     context.applicationContext,
                     CibertecDatabase::class.java,
                     DATABASE_NAME
                    ).build()
                }
            return INSTANCE
        }
    }
}