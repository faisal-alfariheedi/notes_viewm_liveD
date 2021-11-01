package com.example.notes_viewM_liveD

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import notes_viewM_liveD.R

class MainActivity : AppCompatActivity() {
    lateinit var edn: EditText
    lateinit var add: Button
    lateinit var rv: RecyclerView
    lateinit var db: NoteDao
//    lateinit var list:List<Note>
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        add.setOnClickListener{
            if(edn.text.isNotEmpty()){
               addedi(edn.text.toString())
                edn.text.clear()
            }
        }
    }
    fun init(){
        edn=findViewById(R.id.ednote)
        add=findViewById(R.id.add)
        rv=findViewById(R.id.rvm)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)
        var ad=RVAdapter(this)
        rv.adapter = ad
        mvm.getAll().observe(this,{
            ad.setNote(it)
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show()
        })

    }
    fun addedi(note:String){
        mvm.addedit(note)
    }
    fun addedi(note:Note){
        mvm.addedit(note)
    }
    fun del(note:Note){
        mvm.delete(note)
    }




}