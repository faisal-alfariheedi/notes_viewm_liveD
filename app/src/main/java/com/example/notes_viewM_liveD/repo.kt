package com.example.notes_viewM_liveD

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class repo {
    var db:NoteDao
    var list : LiveData<List<Note>>

    constructor(cont: Application) {
        db=NoteDB.getInstance(cont).NoteDao()
        list=db.getall()
    }

    fun addedit(note:Note){
        insnote(db).execute(note)
    }
    fun delete(note:Note){
        delnote(db).execute(note)
    }
    fun getAll():LiveData<List<Note>>{
        return list
    }
    class insnote(var db: NoteDao) : AsyncTask<Note, Void, String>(){
        override fun doInBackground(vararg p0: Note?): String {
            db.addeditNote(p0[0]!!)
            return ""
        }
    }
    class delnote(var db: NoteDao) : AsyncTask<Note, Void, String>(){
        override fun doInBackground(vararg p0: Note?): String {
            db.deleteNote(p0[0]!!)
            return ""
        }
    }
}