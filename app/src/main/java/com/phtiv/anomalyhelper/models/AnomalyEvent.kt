package com.phtiv.anomalyhelper.models

import java.io.Serializable

class AnomalyEvent : Serializable {
        var ID: Int = 0
        var DATE: String = ""
        var LOCATION: String = ""
        var ANOMALY_NAME: String = ""
        var ANOMALY_ID: Int = 0
        var RES_SCORE: String = ""
        var ENL_SCORE: String = ""
        var WINNER: String = ""
        var TYPE: String = ""
}