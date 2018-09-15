package com.phtiv.anomalyhelper.presenters.eventlist

import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.fancyutils.PresenterUtils
import com.phtiv.anomalyhelper.models.AnomalySeries

class EventListHeaderPresenter private constructor(root: View, viewModel: AnomalySeries) {

    private var baseView: View? = null
    private var titleLabel: TextView? = null

    init {
        bindViews(root)
        present(viewModel)
    }

    private fun present(vm: AnomalySeries) {

        PresenterUtils.setText(titleLabel, vm.NAME)

    }

    private fun bindViews(view: View) {
        baseView = view
        titleLabel = view.findViewById(R.id.eventlist_titlelabel)
    }

    companion object {

        /**
         * instantiate a new view and present the view model. it is the responsibility of the caller
         * to handle adding the view to an actual container.
         *
         * @param inflater  [LayoutInflater]
         * @param viewGroup [ViewGroup]
         * @param viewModel [FlightChangePriceDiffNewTripViewModel]
         * @return PageFlightCardPresenter
         */
        fun createInstance(inflater: LayoutInflater, viewGroup: ViewGroup, viewModel: AnomalySeries): Pair<EventListHeaderPresenter, View> {
            val view = inflater.inflate(R.layout.eventlist_header_layout, viewGroup, false)
            return Pair(EventListHeaderPresenter(view, viewModel), view)
        }
    }

}