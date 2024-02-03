package edu.jorgefabro.mylittleshoppinglist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.models.EstablecimientosData
import kotlinx.android.synthetic.main.activity_estaadapter.view.*

//Clase adaptadora de los establecimientos


class EstaAdapter(val context: Context, private val listE: List<EstablecimientosData>) :
    RecyclerView.Adapter<EstaAdapter.ViewHolder>() {
    var items = ArrayList<EstablecimientosData>()

    init {
        items = listE as ArrayList<EstablecimientosData>
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            val item = items[position]
            holder.nombreE.text = item.nombreE
            holder.direc.text = item.direccion
            holder.telf.text = item.telf
            holder.url.text = item.url.lowercase()
        } catch (e: Exception) {
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.activity_estaadapter, parent, false)

        /*try {
            view.twTelf.setOnClickListener(){
                if (ContextCompat.checkSelfPermission(this.context, android.Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                }
                else{
                    val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:601263353"))
                    startActivity(callIntent)
                }

                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE))
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),324)

                    Toast.makeText(this.context,"Se rechazaron los permisos del teléfono.", Toast.LENGTH_LONG).show()
                }
                else
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),324)
                }
            }

        }
        catch (e: Exception){}*/
        return ViewHolder(view)
    }

    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        view: View
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            324 -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("DEBUG", "Permiso concedido.")
                    val callIntent =
                        Intent(Intent.ACTION_CALL, Uri.parse(view.twTelf.text.toString()))
                    startActivity(callIntent)
                } else {
                    Log.d("DEBUG", "Permiso denegado.")
                }
            }
        }
    }*/

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreE: TextView = view.twNombreE
        val direc: TextView = view.twDireccion
        val telf: TextView = view.twTelf
        val url: TextView = view.twUrl
    }

    fun setData(listData: List<EstablecimientosData>) {
        items = listData as ArrayList<EstablecimientosData>
        notifyDataSetChanged()
    }
}
