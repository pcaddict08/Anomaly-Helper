package com.phtiv.anomalyhelper.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.models.AnomalyHistory
import com.phtiv.anomalyhelper.models.AnomalySeries
import kotlinx.android.synthetic.main.fragment_upcoming_anomaly_list.view.*


/**
 * A placeholder fragment containing a simple view.
 */
class UpcomingAnomalyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_upcoming_anomaly_list, container, false)
        rootView.upcoming_map_button?.setOnClickListener {
            //TODO OPEN MAP VIEW
        }
        //upcoming_map_button

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
            //setupAdapter(AnomalySeries.getUpcoming(), AnomalyHistory.getUpcoming())
        super.onActivityCreated(savedInstanceState)
    }

    fun setupAdapter(seriesList: List<AnomalySeries>?, historyList: List<AnomalyHistory>?) {
        if (seriesList != null && historyList != null) {
            //view?.upcoming_recyclerview?.layoutManager = LinearLayoutManager(context)
            //if (view?.upcoming_recyclerview?.adapter == null)
            //    view?.upcoming_recyclerview?.adapter = context?.let { AnomalySeriesAdapter(seriesList, it) }
        }
    }


    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(): UpcomingAnomalyFragment {
            return UpcomingAnomalyFragment()
        }
    }
}