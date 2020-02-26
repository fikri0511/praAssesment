package org.d3if1071.praasses


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if1071.praasses.databinding.FragmentUtamaBinding

/**
 * A simple [Fragment] subclass.
 */
class Utama : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentUtamaBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_utama,container,false)

        binding.bPersegi.setOnClickListener{v:View->
            v.findNavController().navigate(R.id.action_utama_to_persegiPanjang)
        }
        binding.bSegitiga.setOnClickListener{v:View->
            v.findNavController().navigate(R.id.action_utama_to_segitiga)
        }
        setHasOptionsMenu(true)
        return  binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.id_about){
            findNavController().navigate(R.id.action_utama_to_about)
        }
        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController())||super.onOptionsItemSelected(item)
    }


}
