package edu.jorgefabro.mylittleshoppinglist.ui.add.establecimientos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.dbhelper.DbHelpertAplication
import edu.jorgefabro.mylittleshoppinglist.models.EstablecimientosData
import kotlinx.android.synthetic.main.fragment_add_establecimientos.*
import kotlinx.android.synthetic.main.fragment_add_establecimientos.view.*
import java.util.ArrayList

    // Carga el Fragment para añadir los establecimientos a la base de datos

class AddEstablecimientos : Fragment() {

    var listData: ArrayList<EstablecimientosData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_establecimientos, container, false)
        spinner(view)
        setListener(view)
        // Inflate the layout for this fragment
        return view
    }

    private fun setListener(view: View) {
        view.btnAcepEsta.setOnClickListener {
            if (etTelf.length() != 9 || etTelf.text.toString() == "" || etNombreE.text.toString() == "" || etDireccion.text.toString() == "") {
                Toast.makeText(
                    this.requireContext(),
                    "Faltan datos por rellenar.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                var nombreEe: String = etNombreE.text.toString()
                var direcc: String =spinner.selectedItem.toString() + " " + etDireccion.text.toString()
                var tlf: String = "+34 " + etTelf.text.toString()
                var url: String = "https://www." + etNombreE.text.toString() + ".com"

                //val dbHelper = DBHelperEstablecimientos(this, null)
                //dbHelper.addEstablecimientos(nombreEe, direcc, tlf, url)
                DbHelpertAplication.dataSourceE.addEstablecimientos(nombreEe, direcc, tlf, url)
                listData.add(EstablecimientosData(id, nombreEe, direcc, tlf, url))

                Toast.makeText(
                    this.requireContext(),
                    "Se ha añadido el nuevo establecimiento correctamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setData()
    }

    private fun setData() {
        /*var establecimientos = DbHelpertAplication.dataSourceE.getAmigoTodo()
        tvResult.text=""
        establecimientos.forEach{
            tvResult.append("${it.id} - ${it.nombreE} ${it.direccion} $\n")
        }*/
    }

    private fun spinner(view: View) {
        val carreteras = resources.getStringArray(R.array.Carreteras)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this.requireContext(),
                R.layout.activity_detail, carreteras
            )
            view.spinner.adapter = adapter
        }
    }

}