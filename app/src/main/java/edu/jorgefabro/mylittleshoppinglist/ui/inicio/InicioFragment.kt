package edu.jorgefabro.mylittleshoppinglist.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.jorgefabro.mylittleshoppinglist.R

class InicioFragment : Fragment() {

    //  Fragment de bienvenida al usuario

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)



        return view
    }



}