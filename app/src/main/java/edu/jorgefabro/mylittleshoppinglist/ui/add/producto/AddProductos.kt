package edu.jorgefabro.mylittleshoppinglist.ui.add.producto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.dbhelper.DbHelpertAplication
import edu.jorgefabro.mylittleshoppinglist.models.HistoricoData
import edu.jorgefabro.mylittleshoppinglist.models.ProductosData
import kotlinx.android.synthetic.main.fragment_add_productos.*
import kotlinx.android.synthetic.main.fragment_add_productos.view.*
import kotlinx.android.synthetic.main.fragment_productos.view.*
import java.util.*
import kotlin.collections.ArrayList

// Carga el Fragment para añadir los productos y el histórico a la base de datos

class AddProductos : Fragment() {

    var listData: ArrayList<ProductosData> = ArrayList()
    var listDataH: ArrayList<HistoricoData> = ArrayList()
    private lateinit var spinnerTienda: Spinner
    var listEsta1 = ArrayList<String>()

    //var precioProductoTotal: String = ""
    //var precioCompra: Float = 0.00f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_productos, container, false)

        setDate()
        setListener(view)
        //spinner(view)
        // Inflate the layout for this fragment
        return view
    }

    private fun setListener(view: View) {
        try{
            view.btnAcepList.setOnClickListener {
                if (etNombreP.text.toString() == "" || etCant.text.toString() == "" || etPrecio.text.toString() == "") {
                    Toast.makeText(
                        this.requireContext(),
                        "Faltan datos por rellenar.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    var nombrePp: String = etNombreP.text.toString()
                    var cantt: String = etCant.text.toString() + " Ud./Uds."
                    var precioo: String = etPrecio.text.toString() + " €"
                    var establecimientoss: String = spinnerTiendas.selectedItem.toString()

                    /*var precioTotal: Float = view.etCant.text.toString().toFloat() *
                            view.etPrecio.text.toString().toFloat()
                    precioCompra += precioTotal
                    precioProductoTotal = String.format("%.2f", precioCompra)
                    view.twPrecioTotal.text = precioProductoTotal*/

                    //val dbHelper = DBHelperProductos(this, null)
                    //dbHelper.addProducto(nombrePp, cantt, precioo)
                    DbHelpertAplication.dataSourceP.addProducto(
                        nombrePp,
                        cantt,
                        precioo,
                        establecimientoss/*, selectt*/
                    )
                    listData.add(ProductosData(id, nombrePp, cantt, precioo, establecimientoss))

                    DbHelpertAplication.dataSourceH.addHistorico(
                        nombrePp,
                        cantt,
                        precioo,
                        establecimientoss,
                        dia,
                        mes,
                        anyo
                    )/*, selectt*/

                    listDataH.add(
                        HistoricoData(
                            id,
                            nombrePp,
                            cantt,
                            precioo,
                            establecimientoss,
                            dia,
                            mes,
                            anyo
                        )
                    )

                    Toast.makeText(
                        this.requireContext(),
                        "Se ha añadido el nuevo producto correctamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        catch (e: Exception){
        }
    }


    override fun onStart() {
        super.onStart()

        //private fun spinner(view: View) {
        /* val tiendas = resources.getStringArray(R.array.Tiendas)
         val spinner2 = view.findViewById<Spinner>(R.id.spinnerTiendas)
         if (spinner2 != null) {
             val adapter = ArrayAdapter(
                 this.requireContext(),
                 R.layout.activity_detail_tiendas, tiendas
             )
             view.spinnerTiendas.adapter = adapter
         }*/
        spinnerTienda = spinnerTiendas
        listEsta1 =
            DbHelpertAplication.dataSourceE.getEstablecimientosSpinner() as ArrayList<String>

        var listEsta = ArrayList<String>()

        listEsta1.forEach {
            var esta = it.toString()
            if (!listEsta.contains(esta)) {
                listEsta.add(esta)
            }
        }

        val adapterSpinner = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item,
            listEsta
        )
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTienda.adapter = adapterSpinner
        //}
    }

    var dia: Int = 0
    var mes: Int = 0
    var anyo: Int = 0
    private fun setDate() {
        val hoy = Calendar.getInstance()
        dia = hoy.get(Calendar.DAY_OF_MONTH)
        mes = hoy.get(Calendar.MONTH) + 1
        anyo = hoy.get(Calendar.YEAR)
    }
}