package com.rich.challenge4.database

import androidx.room.*

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Notes>

    @Insert
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)
}