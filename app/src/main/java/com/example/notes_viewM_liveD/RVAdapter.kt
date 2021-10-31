package com.example.notes_viewM_liveD

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import notes_viewM_liveD.R

class RVAdapter( val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    private lateinit var rv: List<Note>
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvv = rv[position].note
        holder.itemView.apply {
            var rvlisting= findViewById<CardView>(R.id.rvlisting)
            var ct= findViewById<TextView>(R.id.cardtitle)
            var ed=findViewById<ImageButton>(R.id.edbut)
            var del=findViewById<ImageButton>(R.id.delbut)
            ct.text = rvv.toString()
            ed.setOnClickListener{
                alert(rv[position])
            }
            del.setOnClickListener{

                if(cont is MainActivity)
                   cont.del(rv[position])
            }


        }
    }
    override fun getItemCount() = rv.size

    fun setNote(n:List<Note>){
        rv=n
        notifyDataSetChanged()
    }

    fun alert(note: Note) {
        var n=note
        var d= AlertDialog.Builder(cont)
        d.setTitle("Edit note")
        d.setCancelable(false)
        var Ed= EditText(cont)
        d.setPositiveButton("Change") { _, _ ->
            n.note = Ed.text.toString()
            if (cont is MainActivity) cont.addedi(n)
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setView(Ed)
        d.show()
    }


}