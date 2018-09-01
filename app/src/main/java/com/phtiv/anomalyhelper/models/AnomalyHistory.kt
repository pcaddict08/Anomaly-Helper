package com.phtiv.anomalyhelper.models

data class AnomalyHistory(
        val DATE: String = "",
        val LOCATION: String = "",
        val ANOMALY_NAME: String = "",
        val ANOMALY_ID: Int = 0,
        val RES_SCORE: String = "",
        val ENL_SCORE: String = "",
        val WINNER: String = "",
        val TYPE: String = ""
)