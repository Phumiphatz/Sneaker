package com.example.sneaky

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.DatabaseView

class dataViewModelRepository(private val Dao: DAOSneaky) {

    val allSneaky: LiveData<List<sneakyDatabaseModels>> = Dao.get()

    fun insert(sneaky: sneakyDatabaseModels) {
        Dao.insert(sneaky)
    }

    fun clear() {
        Dao.clear()
    }
    fun get (){
        Dao.get()
    }

}