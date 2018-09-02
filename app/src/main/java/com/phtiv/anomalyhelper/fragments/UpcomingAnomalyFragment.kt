package com.phtiv.anomalyhelper.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.adapters.AnomalySeriesAdapter
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.retrofit.RetrofitCalls
import com.phtiv.anomalyhelper.retrofit.RetrofitClientInstance
import com.phtiv.anomalyhelper.utils.AlertHelper
import com.phtiv.anomalyhelper.utils.ProgressHelper
import kotlinx.android.synthetic.main.fragment_anomaly_list_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A placeholder fragment containing a simple view.
 */
class UpcomingAnomalyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_anomaly_list_layout, container, false)
        rootView.upcoming_map_button?.setOnClickListener {
            //TODO OPEN MAP VIEW
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AnomalySeries.handleSeriesRequest(view, AnomalySeries.CREATOR.Type.UPCOMING)
        super.onActivityCreated(savedInstanceState)
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

