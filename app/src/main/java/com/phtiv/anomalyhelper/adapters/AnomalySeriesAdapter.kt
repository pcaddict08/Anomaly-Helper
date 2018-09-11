package com.phtiv.anomalyhelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.models.AnomalyEvent
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import com.phtiv.anomalyhelper.utils.AlertHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnomalySeriesAdapter(private val list: List<AnomalySeries>, private val context: Context) : RecyclerView.Adapter<AnomalySeriesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var namelabel_storerow: TextView
        var medalicon_anomalyseriesrow: ImageView
        var base_layout: LinearLayout

        init {
            medalicon_anomalyseriesrow = v.findViewById(R.id.medalicon_anomalyseriesrow)
            namelabel_storerow = v.findViewById(R.id.namelabel_anomalyseriesrow)
            base_layout = v as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AnomalySeriesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_anomalyseries, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = list[position]
        holder.namelabel_storerow.text = series.NAME
        holder.medalicon_anomalyseriesrow.setImageDrawable(series.getIconDrawable(context))
        holder.base_layout.setOnClickListener(View.OnClickListener { it ->
            val service = RetrofitClientInstance.retrofitInstance?.create<RetrofitCalls>(RetrofitCalls::class.java)
            val call = service?.getEventByID(series.ID)
            call?.enqueue(object : Callback<List<AnomalyEvent>> {
                override fun onResponse(call: Call<List<AnomalyEvent>>, response: Response<List<AnomalyEvent>>) {
                    var list = response.body()
                }

                override fun onFailure(call: Call<List<AnomalyEvent>>, t: Throwable) {
                    holder.base_layout.context?.let { AlertHelper.showToast(it, t.localizedMessage, true, R.drawable.ic_warning) }
                }
            })
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return list.size
    }
}