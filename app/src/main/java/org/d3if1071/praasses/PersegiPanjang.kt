package org.d3if1071.praasses


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import org.d3if1071.praasses.databinding.FragmentPersegiPanjangBinding

/**
 * A simple [Fragment] subclass.
 */
const val KEY_KELILING = "panjang_key"
const val KEY_LUAS = "lebar_key"
class PersegiPanjang : Fragment() {
var panjang=0
    var lebar= 0
    var hasilKelilingAkhir=0
    var hasilLuasAkhir=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(
            inflater,R.layout.fragment_persegi_panjang,container,false
        )
        binding.tvKelilingpp.visibility=View.GONE
        binding.tvLuaspp.visibility=View.GONE
        binding.bShare.visibility=View.GONE

         hasilKelilingAkhir=0
         hasilLuasAkhir=0
        if (savedInstanceState != null) {
            // Get all the game state information from the bundle, set it
            hasilKelilingAkhir = savedInstanceState.getInt(KEY_KELILING, 0)
            hasilLuasAkhir = savedInstanceState.getInt(KEY_LUAS, 0)
            binding.tvLuaspp.visibility=View.VISIBLE
            binding.tvKelilingpp.visibility=View.VISIBLE
            binding.bShare.visibility=View.VISIBLE

        }

        binding.bHitungpp.setOnClickListener { view:View->
            panjang = binding.etPanjang.text.toString().toInt()
            lebar = binding.etLebar.text.toString().toInt()
            hasilKelilingAkhir = 2*(panjang+lebar)
            hasilLuasAkhir=panjang*lebar

            binding.tvKelilingpp.setText("Keliling : " + hasilKelilingAkhir.toString())
            binding.tvLuaspp.setText("Luas : "+hasilLuasAkhir.toString())

            binding.tvLuaspp.visibility=View.VISIBLE
            binding.tvKelilingpp.visibility=View.VISIBLE
            binding.bShare.visibility=View.VISIBLE

        }

        binding.bShare.setOnClickListener {
            view:View->

            val mIntent = Intent(Intent.ACTION_SEND)

            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"

            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("fikrisaja05@gmail.com"))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "Jawaban")
            mIntent.putExtra(Intent.EXTRA_TEXT, "Keliling : "+hasilKelilingAkhir.toString()+"\n Luas : "+hasilLuasAkhir.toString())
            try {
                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            }
            catch (e: Exception){

            }
        }

        setHasOptionsMenu(true)
        binding.tvKelilingpp.setText("Keliling : " + hasilKelilingAkhir.toString())
        binding.tvLuaspp.setText("Luas : "+hasilLuasAkhir.toString())
        return binding.root

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_LUAS, hasilLuasAkhir)
        outState.putInt(KEY_KELILING, hasilKelilingAkhir)

        super.onSaveInstanceState(outState)
    }



}
