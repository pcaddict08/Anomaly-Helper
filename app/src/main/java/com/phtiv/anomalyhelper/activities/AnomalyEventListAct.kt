package com.phtiv.anomalyhelper.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.phtiv.anomalyhelper.AnomalyAct
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.models.AnomalyEvent
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.presenters.eventlist.EventListHeaderPresenter
import com.phtiv.anomalyhelper.utils.ThemeHelper
import kotlinx.android.synthetic.main.eventlist_activity_layout.*

class AnomalyEventListAct : AnomalyAct() {

    var eventList: List<AnomalyEvent> = listOf()
    lateinit var parent: AnomalySeries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eventlist_activity_layout)
        eventList = intent.getParcelableArrayListExtra(INTENT_EVENTLIST)
        parent = intent.getParcelableExtra(INTENT_PARENT)

        ThemeHelper.initActionBar(supportActionBar, parent.getTitleString(this, AnomalySeries.TITLETYPE.EVENTLIST, isSimple = true))
        val inflater = LayoutInflater.from(this)
        if (inflater != null)
        {
            val headerView = EventListHeaderPresenter.createInstance(inflater, eventlist_header_layout, parent).second
            if (headerView != null)
                eventlist_header_layout.addView(headerView)
        }
        AnomalyEvent.setupRecyclerViewAdapter(eventList, view = view)
    }


    companion object {

        private val INTENT_EVENTLIST = "event_list"
        private val INTENT_PARENT = "parent_series"

        fun newIntent(context: Context, eventList: ArrayList<AnomalyEvent>, series: AnomalySeries): Intent {
            val intent = Intent(context, AnomalyEventListAct::class.java)
            intent.putParcelableArrayListExtra(INTENT_EVENTLIST, eventList)
            intent.putExtra(INTENT_PARENT, series)
            return intent
        }
    }
}
