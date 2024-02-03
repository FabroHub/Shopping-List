package edu.jorgefabro.mylittleshoppinglist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.databinding.ActivityProductosadapterBinding
import edu.jorgefabro.mylittleshoppinglist.models.ProductosData
import kotlinx.android.synthetic.main.activity_productosadapter.view.*

//Clase adaptadora de los productos

class ProductoAdapter(val context: Context, private val listP: List<ProductosData>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    private var items = ArrayList<ProductosData>()

    init {
        items = listP as ArrayList<ProductosData>
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            val item = items[position]
            holder.nombre.text = item.nombre
            holder.cant.text = item.cant
            holder.precio.text = item.precio
            holder.establecimientos.text = item.establecimientos
        } catch (e: Exception) {
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.activity_productosadapter, parent, false)

        return ViewHolder(view)

    }

    fun setData(listData: List<ProductosData>) {
        items = listData as ArrayList<ProductosData>
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.twNombreP
        val cant: TextView = view.twCantidad
        val precio: TextView = view.twPrecio
        val establecimientos: TextView = view.twEsta
    }

}
