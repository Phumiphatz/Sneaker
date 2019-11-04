package com.example.sneaky

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao

interface DAOSneaky {

        @Insert
        fun insert(sneaky: sneakyDatabaseModels)

        @Query("SELECT * FROM sneakers ORDER BY sneakyId ASC")
        fun get(): LiveData<List<sneakyDatabaseModels>>

        @Query("DELETE FROM sneakers")
        fun clear()

    }
