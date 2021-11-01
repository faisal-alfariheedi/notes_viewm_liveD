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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import notes_viewM_liveD.R
import notes_viewM_liveD.main

class RVAdapter( val cont: Fragment): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    private var rv: List<Note> = listOf()
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
            rvlisting.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_main_to_view)
                Vm.pocket=rv[position]
            }
            del.setOnClickListener{

                if(cont is main)
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
        var d= AlertDialog.Builder(cont.requireContext())
        d.setTitle("Edit note")
        d.setCancelable(false)
        var Ed= EditText(cont.requireContext())
        d.setPositiveButton("Change") { _, _ ->
            n.note = Ed.text.toString()
            if (cont is main) cont.addedi(note)
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setView(Ed)
        d.show()
    }


}