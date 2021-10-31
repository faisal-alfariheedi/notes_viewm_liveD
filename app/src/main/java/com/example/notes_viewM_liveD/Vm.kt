package com.example.notes_viewM_liveD

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class Vm(application: Application) : AndroidViewModel(application) {
    var rep=repo(application)
    private var list=rep.getAll()

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