package edu.jorgefabro.mylittleshoppinglist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.models.HistoricoData
import kotlinx.android.synthetic.main.activity_historicoadapter.view.*

//Clase adaptadora del histórico

class HistoricoAdapter(val context: Context, private val listH: List<HistoricoData>) :
    RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    private var items = ArrayList<HistoricoData>()

    init {
        items = listH as ArrayList<HistoricoData>
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            val item = items[position]

            lateinit var mes2: String

            if (item.mes == 1) {
                mes2 = "Enero"
            } else if (item.mes == 2) {
                mes2 = "Febrero"
            } else if (item.mes == 3) {
                mes2 = "Marzo"
            } else if (item.mes == 4) {
                mes2 = "Abril"
            } else if (item.mes == 5) {
                mes2 = "Mayo"
            } else if (item.mes == 6) {
                mes2 = "Junio"
            } else if (item.mes == 7) {
                mes2 = "Julio"
            } else if (item.mes == 8) {
                mes2 = "Agosto"
            } else if (item.mes == 9) {
                mes2 = "Septiembre"
            } else if (item.mes == 10) {
                mes2 = "Octubre"
            } else if (item.mes == 11) {
                mes2 = "Noviembre"
            } else {
                mes2 = "Diciembre"
            }

            holder.nombre2.text = item.nombreh
            holder.cant2.text = item.canth
            holder.precio2.text = item.precioh
            holder.establecimientos2.text = item.establecimientosh
            holder.dia2.text = item.dia.toString()
            holder.mes2.text = mes2
            holder.anyo2.text = item.anyo.toString()


        } catch (e: Exception) {
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.activity_historicoadapter, parent, false)

        return ViewHolder(view)

    }

    fun setData(listData: MutableList<HistoricoData>) {
        items = listData as ArrayList<HistoricoData>
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nombre2: TextView = view.twNombreP2
        val cant2: TextView = view.twCantidad2
        val precio2: TextView = view.twPrecio2
        val establecimientos2: TextView = view.twEsta2
        val dia2: TextView = view.twDia
        val mes2: TextView = view.twMes
        val anyo2: TextView = view.twAnyo
    }
}
