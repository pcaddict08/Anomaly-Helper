package com.phtiv.anomalyhelper.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        var medalicon_anomalyeventsrow: ImageView
        var winnerscorelabel_anomalyeventsrow: TextView
        var loserscorelabel_anomalyeventsrow: TextView
        var base_layout: LinearLayout

        init {
            medalicon_anomalyeventsrow = v.findViewById(R.id.medalicon_anomalyeventsrow)
            namelabel_anomalyeventsrow = v.findViewById(R.id.namelabel_anomalyeventsrow)
            winnerscorelabel_anomalyeventsrow = v.findViewById(R.id.winnerscorelabel_anomalyeventsrow)
            loserscorelabel_anomalyeventsrow = v.findViewById(R.id.loserscorelabel_anomalyeventsrow)
            base_layout = v as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AnomalyEventsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_anomalyevents, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = list[position]
        val winnerColor = ContextCompat.getColor(context, GeneralUtil.getWinningColor(event.WINNER))
        val loserColor = ContextCompat.getColor(context, GeneralUtil.getLoserColor(event.WINNER))
        holder.namelabel_anomalyeventsrow.text = event.LOCATION.CITY
        holder.namelabel_anomalyeventsrow.setTextColor(winnerColor)
        holder.medalicon_anomalyeventsrow.setImageDrawable(event.getIconDrawable(context))
        holder.winnerscorelabel_anomalyeventsrow.setTextColor(winnerColor)
        holder.winnerscorelabel_anomalyeventsrow.text = GeneralUtil.getWinningScore(event.WINNER, event.RES_SCORE, event.ENL_SCORE)
        holder.loserscorelabel_anomalyeventsrow.setTextColor(loserColor)
        holder.loserscorelabel_anomalyeventsrow.text = GeneralUtil.getLosingScore(event.WINNER, event.RES_SCORE, event.ENL_SCORE)
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