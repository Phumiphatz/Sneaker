package com.example.sneaky

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [sneakyDatabaseModels::class], version = 2, exportSchema = false)

abstract class sneakyDatabase : RoomDatabase() {

    abstract fun sneakyDatabaseModels(): DAOSneaky

    companion object {

        @Volatile
        private var INSTANCE: sneakyDatabase? = null

        fun getInstance(context: Context): sneakyDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    sneakyDatabase::class.java,
                    "database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }
        }
    }

}