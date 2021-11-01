package notes_viewM_liveD

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_viewM_liveD.Note
import com.example.notes_viewM_liveD.NoteDao
import com.example.notes_viewM_liveD.RVAdapter
import com.example.notes_viewM_liveD.Vm

class main : Fragment() {

    lateinit var edn: EditText
    lateinit var add: Button
    lateinit var rv: RecyclerView
    lateinit var db: NoteDao
    //    lateinit var list:List<Note>
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_main, container, false)

        init(v)
        add.setOnClickListener{
            if(edn.text.isNotEmpty()){
                addedi(edn.text.toString())
                edn.text.clear()
            }
        }


        return v
    }
    fun init(v: View) {
        edn=v.findViewById(R.id.ednote)
        add=v.findViewById(R.id.add)
        rv=v.findViewById(R.id.rvm)
        rv.layoutManager = LinearLayoutManager(v.context)
        var ad= RVAdapter(this)
        rv.adapter = ad
        mvm.getAll().observe(viewLifecycleOwner,{
            ad.setNote(it)
            Toast.makeText(v.context,"updated", Toast.LENGTH_SHORT).show()
        })

    }
    fun addedi(note:String){
        mvm.addedit(note)
    }
    fun addedi(note: Note){
        mvm.addedit(note)
    }
    fun del(note: Note){
        mvm.delete(note)
    }


}