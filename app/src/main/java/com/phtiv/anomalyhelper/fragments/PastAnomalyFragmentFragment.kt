package com.phtiv.anomalyhelper.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phtiv.anomalyhelper.R
import kotlinx.android.synthetic.main.fragment_anomaly_lists.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class PastAnomalyFragmentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_anomaly_lists, container, false)
        //TODO SET UP UI HERE
        return rootView
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(): PastAnomalyFragmentFragment {
            return PastAnomalyFragmentFragment()
        }
    }
}