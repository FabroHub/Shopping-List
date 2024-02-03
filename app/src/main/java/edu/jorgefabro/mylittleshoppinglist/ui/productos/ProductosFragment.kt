package edu.jorgefabro.mylittleshoppinglist.ui.productos

import android.app.AlertDialog
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.adapters.ProductoAdapter
import edu.jorgefabro.mylittleshoppinglist.dbhelper.DbHelpertAplication
import edu.jorgefabro.mylittleshoppinglist.models.EstablecimientosData
import edu.jorgefabro.mylittleshoppinglist.models.ProductosData
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.activity_productosadapter.*
import kotlinx.android.synthetic.main.activity_productosadapter.view.*
import kotlinx.android.synthetic.main.fragment_add_productos.view.*
import kotlinx.android.synthetic.main.fragment_productos.view.*

    // Fragment para ver los productos añadidos en la base de datos

class ProductosFragment : Fragment() {

    var listData: MutableList<ProductosData> = ArrayList()
    lateinit var adapter: ProductoAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        initRV(view)

        addProducto(view)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun addProducto(view: View) {
        view.fab2.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.addProductos)
        }
    }

    private fun initRV(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvProductos)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ProductoAdapter(this.requireContext(), listData)

        recyclerView.adapter = adapter

        val itemSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                showDialog(viewHolder)


                //listData.removeAt(position)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    .addActionIcon(R.drawable.ic_baseline_delete_outline_24)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

        val swap = ItemTouchHelper(itemSwipe)
        swap.attachToRecyclerView(recyclerView)
    }

    fun showDialog(viewHolder: RecyclerView.ViewHolder) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Eliminar")
        builder.setMessage("¿Está seguro que quiere eliminar este dato?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            val position = viewHolder.adapterPosition
            DbHelpertAplication.dataSourceP.delProducto(listData[position].id)
            listData.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(
                this.requireContext(),
                "El dato se ha borrado correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("Cancelar") { dialog, wich ->
            val position = viewHolder.adapterPosition
            adapter.notifyItemChanged(position)
            Toast.makeText(
                this.requireContext(),
                "No se ha borrado ningún dato",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        listData = DbHelpertAplication.dataSourceP.getTodosProductos() as MutableList<ProductosData>
        adapter.setData(listData)
    }
}