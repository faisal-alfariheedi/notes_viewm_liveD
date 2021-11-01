package com.example.notes_viewM_liveD

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class Vm(application: Application) : AndroidViewModel(application) {
    var rep=repo(application,1)//this will select local db or firebase 0 for local 1 for firebase
    private var list=rep.getAll()

    fun addedit(note:String){
        rep.addedit(Note(0,note))
    }
    fun addedit(note:Note){
        rep.addedit(note)
    }
    fun delete(note:Note){
        rep.delete(note)
    }
    fun getAll():LiveData<List<Note>>{
        return list
    }

}