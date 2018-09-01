package com.phtiv.anomalyhelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.models.AnomalySeries


class AnomalySeriesAdapter(private val list: List<AnomalySeries>, private val context: Context) : RecyclerView.Adapter<AnomalySeriesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var namelabel_storerow: TextView

        init {
            namelabel_storerow = v.findViewById(R.id.namelabel_anomalyseriesrow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AnomalySeriesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_anomalyseries, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = list[position]
        holder.namelabel_storerow.text = series.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return list.size
    }
}