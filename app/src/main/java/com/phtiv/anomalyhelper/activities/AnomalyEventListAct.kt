package com.phtiv.anomalyhelper.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.phtiv.anomalyhelper.AnomalyAct
import com.phtiv.anomalyhelper.R
import com.phtiv.anomalyhelper.models.AnomalyEvent
import com.phtiv.anomalyhelper.models.AnomalySeries
import com.phtiv.anomalyhelper.utils.ThemeHelper
import kotlinx.android.synthetic.main.activity_anomaly_event_list.*

class AnomalyEventListAct : AnomalyAct() {

    var eventList: List<AnomalyEvent> = listOf()
    lateinit var parent: AnomalySeries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anomaly_event_list)
        eventList = intent.getParcelableArrayListExtra(INTENT_EVENTLIST)
        parent = intent.getParcelableExtra(INTENT_PARENT)

        ThemeHelper.initActionBar(supportActionBar, parent.getTitleString(this, AnomalySeries.TITLETYPE.EVENTLIST))
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
