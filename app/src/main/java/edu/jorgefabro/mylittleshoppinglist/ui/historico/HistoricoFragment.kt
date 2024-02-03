package edu.jorgefabro.mylittleshoppinglist.ui.historico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.jorgefabro.mylittleshoppinglist.R
import edu.jorgefabro.mylittleshoppinglist.adapters.HistoricoAdapter
import edu.jorgefabro.mylittleshoppinglist.dbhelper.DbHelpertAplication
import edu.jorgefabro.mylittleshoppinglist.models.HistoricoData

class HistoricoFragment : Fragment() {
    var listData: MutableList<HistoricoData> = ArrayList()
    lateinit var adapter: HistoricoAdapter

    // Fragment para ver el histórico

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_historico, container, false)

        initRV(view)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initRV(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvHistorico)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = HistoricoAdapter(this.requireContext(), listData)

        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        listData = DbHelpertAplication.dataSourceH.getHistorico() as MutableList<HistoricoData>
        adapter.setData(listData)
    }
}