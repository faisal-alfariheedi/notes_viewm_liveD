package com.example.notes_viewM_liveD

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.FirestoreGrpc

class repo {
    val db: NoteDao
    var list: MutableLiveData<List<Note>>
    var sel: Int// this will select local db or firebase 0 for local 1 for firebase
    val firedb: FirebaseFirestore
    var con: Context

    constructor(cont: Application, dsel: Int) {
        db = NoteDB.getInstance(cont).NoteDao()
        sel = dsel
        firedb = Firebase.firestore
        con = cont
        list = MutableLiveData<List<Note>>().apply { postValue(listOf<Note>()) }
        if (sel == 0) {
            list = db.getall() as MutableLiveData<List<Note>>
        } else {
            get()
        }
    }

    fun get(){
        firedb.collection("note")
            .get()
            .addOnSuccessListener {result ->
                var flist=result.documents
                var clist=mutableListOf<Note>()
                for (note in flist.toList()) {
                    clist.add(note.toObject<Note>()!!)
                }
                list.postValue(clist)
            }
    }

    fun addedit(note:Note){
        if(sel==0) {
            insnote(db).execute(note)
        }else{
            get()
            if(note.id==0) {
                if (list.value!!.isNotEmpty()) {
                    note.id = list.value!!.last().id + 1
                }
                firedb.collection("note").document(note.id.toString())
                    .set(note)
                    .addOnSuccessListener { Toast.makeText(con,"note is add/updated",Toast.LENGTH_SHORT).show()
                        get()
                    }
                    .addOnFailureListener { Toast.makeText(con,"updating failed",Toast.LENGTH_SHORT).show() }
            }else{
                firedb.collection("note").document(note.id.toString())
                    .update(mapOf("note" to note.note))
                    .addOnSuccessListener { Toast.makeText(con,"note is add/updated",Toast.LENGTH_SHORT).show()
                        get()
                    }
                    .addOnFailureListener { Toast.makeText(con,"updating failed",Toast.LENGTH_SHORT).show() }
            }

        }
    }
    fun delete(note:Note){
        if(sel==0) {
            delnote(db).execute(note)
        }else{
            get()
            firedb.collection("note").document(note.id.toString()).delete()
            get()
        }
    }
    fun getAll(): LiveData<List<Note>> {
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