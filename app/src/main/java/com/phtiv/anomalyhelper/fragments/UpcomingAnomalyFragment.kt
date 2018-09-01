package com.phtiv.anomalyhelper.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.adapters.AnomalySeriesAdapter
import kotlinx.android.synthetic.main.fragment_upcoming_anomaly_list.view.*
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        super.onActivityCreated(savedInstanceState)
    }

    fun finishedLoading(list: List<AnomalySeries>?) {
        if (list != null) {
            view?.upcoming_recyclerview?.layoutManager = LinearLayoutManager(context)
            if (view?.upcoming_recyclerview?.adapter == null)
                view?.upcoming_recyclerview?.adapter = context?.let { AnomalySeriesAdapter(list, it) }
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