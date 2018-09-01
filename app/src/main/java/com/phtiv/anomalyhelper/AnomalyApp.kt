package com.phtiv.anomalyhelper

import com.raizlabs.android.dbflow.config.FlowManager
import android.app.Application


class AnomalyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }
}