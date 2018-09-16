package com.phtiv.anomalyhelper.presenters.eventlist

import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.fancyutils.PresenterUtils
import com.phtiv.anomalyhelper.models.AnomalySeries
import org.w3c.dom.Text

class EventListHeaderPresenter private constructor(root: View, viewModel: AnomalySeries) {

    private var baseView: View? = null
    private var titleLabel: TextView? = null
    private var dateLabel: TextView? = null
    private var iconView: ImageView? = null
    private var winnerView: ImageView? = null
    private var enlScore: TextView? = null
    private var resScore: TextView? = null
    private var enlLayout: RelativeLayout? = null
    private var resLayout: RelativeLayout? = null

    init {
        bindViews(root)
        present(viewModel)
    }

    private fun present(vm: AnomalySeries) {
        PresenterUtils.setText(titleLabel, vm.NAME)
        PresenterUtils.setText(dateLabel, vm.DATES)
        PresenterUtils.setImage(iconView, vm.ICON_URL)
        PresenterUtils.setImage(winnerView, winnerView?.context?.let { vm.getIconDrawable(context = it) })
        AnomalySeries.formatScoreBar(vm, enlLayout, enlScore, resLayout, resScore)
    }

    private fun bindViews(view: View) {
        baseView = view
        titleLabel = view.findViewById(R.id.eventlist_titlelabel)
        dateLabel = view.findViewById(R.id.eventlist_datelabel)
        winnerView = view.findViewById(R.id.eventlist_winnericonview)
        iconView = view.findViewById(R.id.eventlist_iconview)
        enlScore = view.findViewById(R.id.eventlist_enlscore)
        enlLayout = view.findViewById(R.id.eventlist_enlscorebg)
        resScore = view.findViewById(R.id.eventlist_resscore)
        resLayout = view.findViewById(R.id.eventlist_resscorebg)
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