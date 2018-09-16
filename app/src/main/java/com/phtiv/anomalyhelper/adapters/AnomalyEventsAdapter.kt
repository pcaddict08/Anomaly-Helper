package com.phtiv.anomalyhelper.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.activities.AnomalyEventListAct
import com.phtiv.anomalyhelper.activities.AnomalyListsAct
import com.phtiv.anomalyhelper.models.AnomalyEvent
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import com.phtiv.anomalyhelper.utils.AlertHelper
import com.phtiv.anomalyhelper.utils.GeneralUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnomalyEventsAdapter(private val list: List<AnomalyEvent>, private val context: Context) : RecyclerView.Adapter<AnomalyEventsAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var namelabel_anomalyeventsrow: TextView
        var enlScore: TextView
        var resScore: TextView
        var base_layout: LinearLayout
        var enlLayout: RelativeLayout
        var resLayout: RelativeLayout

        init {
            namelabel_anomalyeventsrow = v.findViewById(R.id.namelabel_anomalyeventsrow)
            enlScore = v.findViewById(R.id.anomalyeventsrow_enlscore)
            resScore = v.findViewById(R.id.anomalyeventsrow_resscore)
            enlLayout = v.findViewById(R.id.anomalyeventsrow_enlscorebg)
            resLayout = v.findViewById(R.id.anomalyeventsrow_resscorebg)
            base_layout = v as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AnomalyEventsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_anomalyevents, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = list[position]
        holder.namelabel_anomalyeventsrow.text = event.LOCATION.CITY
        AnomalySeries.formatScoreBar(event, holder.enlLayout, holder.enlScore, holder.resLayout, holder.resScore)

        holder.base_layout.setOnClickListener({ it ->
            val context = it.context;
            if (context is AnomalyEventListAct) {
                //TODO do something here
                //context.tappedSeries(series)
            }
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return list.size
    }
}