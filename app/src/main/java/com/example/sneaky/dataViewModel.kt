package com.example.sneaky

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class dataViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: dataViewModelRepository
    val all: LiveData<List<sneakyDatabaseModels>>

    init {
        val wordsDao = sneakyDatabase.getInstance(application.applicationContext).sneakyDatabaseModels()
        repository = dataViewModelRepository(wordsDao)
        all = repository.allSneaky

    }
    fun get() = viewModelScope.launch {
        repository.get()
    }

    fun insert(word: sneakyDatabaseModels) = viewModelScope.launch {
        repository.insert(word)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }
}
