package com.phtiv.anomalyhelper.retrofit

import com.phtiv.anomalyhelper.models.AnomalyEvent
import com.phtiv.anomalyhelper.models.AnomalySeries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitCalls {

    @GET("anomalies.json")
    fun allSeries(): Call<List<AnomalySeries>>

    @GET("history.json")
    fun allEvents(): Call<List<AnomalyEvent>>

    @GET("pastseries.json")
    fun pastSeries(): Call<List<AnomalySeries>>

    @GET("upcomingseries.json")
    fun upcomingSeries(): Call<List<AnomalySeries>>

    @GET("?action=get_events")
    fun getEventByID(@Query("anomID") anomID: Int ): Call<List<AnomalyEvent>>
}