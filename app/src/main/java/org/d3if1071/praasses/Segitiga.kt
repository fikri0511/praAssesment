package org.d3if1071.praasses


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.d3if1071.praasses.databinding.FragmentPersegiPanjangBinding
import org.d3if1071.praasses.databinding.FragmentSegitigaBinding

/**
 * A simple [Fragment] subclass.
 */
const val KEY_KELILINGSG = "panjang_key"
const val KEY_LUASSG = "lebar_key"
class Segitiga : Fragment() {
    var hasilKelilingAkhir=0.0
    var hasilLuasAkhir=0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = DataBindingUtil.inflate<FragmentSegitigaBinding>(
            inflater,R.layout.fragment_segitiga,container,false
        )
        binding.tvKelilingsg.visibility=View.GONE
        binding.tvLuassg.visibility=View.GONE
        binding.bSharesg.visibility=View.GONE

         hasilKelilingAkhir=0.0
         hasilLuasAkhir=0.0


        if (savedInstanceState != null) {
            // Get all the game state information from the bundle, set it
            hasilKelilingAkhir = savedInstanceState.getDouble(KEY_KELILINGSG, 0.0)
            hasilLuasAkhir = savedInstanceState.getDouble(KEY_LUASSG, 0.0)
            binding.tvLuassg.visibility=View.VISIBLE
            binding.tvKelilingsg.visibility=View.VISIBLE
            binding.bSharesg.visibility=View.VISIBLE

        }


        binding.bHitungsg.setOnClickListener { view:View->
            var panjang = binding.etPanjangsg.text.toString().toDouble()
             var lebar = binding.etLebarsg.text.toString().toDouble()
            var tinggi = Math.hypot(panjang,lebar)
            hasilKelilingAkhir = panjang+lebar+tinggi
            hasilLuasAkhir=0.5*panjang*lebar

            binding.tvKelilingsg.setText("Keliling : " + hasilKelilingAkhir.toString())
            binding.tvLuassg.setText("Luas : "+hasilLuasAkhir.toString())

            binding.tvLuassg.visibility=View.VISIBLE
            binding.tvKelilingsg.visibility=View.VISIBLE
            binding.bSharesg.visibility=View.VISIBLE

        }

        binding.bSharesg.setOnClickListener {
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
        binding.tvKelilingsg.setText("Keliling : " + hasilKelilingAkhir.toString())
        binding.tvLuassg.setText("Luas : "+hasilLuasAkhir.toString())
        return binding.root

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble(KEY_LUASSG, hasilLuasAkhir)
        outState.putDouble(KEY_KELILINGSG, hasilKelilingAkhir)

        super.onSaveInstanceState(outState)
    }

}
