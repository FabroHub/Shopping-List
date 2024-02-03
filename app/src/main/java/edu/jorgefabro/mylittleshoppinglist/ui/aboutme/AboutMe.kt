package edu.jorgefabro.mylittleshoppinglist.ui.aboutme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import edu.jorgefabro.mylittleshoppinglist.R
import kotlinx.android.synthetic.main.fragment_about_me.*
import kotlinx.android.synthetic.main.fragment_about_me.view.*

class AboutMe : Fragment() {

    // Fragment para los créditos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        msg(view)

        return view
    }

    private fun msg(view: View) {
        view.twAm2.setOnClickListener() {
            Snackbar.make(llAboutMe, "${twAm.text} ${twAm2.text}.", Snackbar.LENGTH_LONG)
                .apply {
                    setAction("Aceptar") {

                    }.show()
                }
        }
    }
}