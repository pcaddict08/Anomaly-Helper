package com.phtiv.anomalyhelper.retrofit

import com.phtiv.anomalyhelper.models.AnomalyHistory
import com.phtiv.anomalyhelper.models.AnomalySeries
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitCalls {

    @GET("anomalies.json")
    fun allSeries(): Call<List<AnomalySeries>>

    @GET("history.json")
    fun allHistory(): Call<List<AnomalyHistory>>
}