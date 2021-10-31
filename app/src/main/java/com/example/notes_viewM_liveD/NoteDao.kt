package com.example.notes_viewM_liveD

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * from Note")
    fun getall(): LiveData<List<Note>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}
