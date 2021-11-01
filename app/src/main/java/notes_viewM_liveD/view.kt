package notes_viewM_liveD

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.notes_viewM_liveD.Vm
import notes_viewM_liveD.R


class view : Fragment() {

    lateinit var vi:TextView
    lateinit var buk: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_view, container, false)
        init(v)

        buk.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_view_to_main)
            }

        return v
    }

    private fun init(v: View) {
        vi=v.findViewById(R.id.textView)
        vi.text= Vm.pocket!!.note
        buk=v.findViewById(R.id.bak)
    }


}