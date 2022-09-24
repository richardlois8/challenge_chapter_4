package com.rich.challenge4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rich.challenge4.database.Notes
import com.rich.challenge4.database.NotesDAO
import com.rich.challenge4.database.NotesTakingDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesViewModel(app:Application) : AndroidViewModel(app) {
    var allNotes : MutableLiveData<List<Notes>?>

    init {
        allNotes = MutableLiveData()
        getAllNotes()
    }

    fun getAllNotesObserver() : MutableLiveData<List<Notes>?> {
        return allNotes
    }

    fun getAllNotes(){
        GlobalScope.launch {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            val listNotes = notesDAO.getAllNotes()
            allNotes.postValue(listNotes)
        }
    }

    fun insertNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.insertNote(notes)
            getAllNotes()
        }
    }

    fun deleteNotes(notes : Notes){
        GlobalScope.launch {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.deleteNote(notes)
            getAllNotes()
        }
    }

    fun updateNotes(notes: Notes){
        GlobalScope.async {
            val notesDAO = NotesTakingDB.getInstance((getApplication()))!!.notesDao()
            notesDAO.updateNote(notes)
            getAllNotes()
        }
    }
}