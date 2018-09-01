package com.phtiv.anomalyhelper.models

import com.phtiv.anomalyhelper.db.AppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite

@Table(database = AppDatabase::class, name = "AnomalyHistory_Table")
class AnomalyHistory () {
        @PrimaryKey
        var ID: Int = 0
        @Column
        var DATE: String = ""
        @Column
        var LOCATION: String = ""
        @Column
        var ANOMALY_NAME: String = ""
        @Column
        var ANOMALY_ID: Int = 0
        @Column
        var RES_SCORE: String = ""
        @Column
        var ENL_SCORE: String = ""
        @Column
        var WINNER: String = ""
        @Column
        var TYPE: String = ""

    companion object {
        fun insertOrReplace(seriesList: List<AnomalyHistory>) {
            val adapter = FlowManager.getModelAdapter<AnomalyHistory>(AnomalyHistory::class.java)
            seriesList.forEach {
                if (adapter.exists(it))
                    adapter.update(it)
                else
                    adapter.insert(it)
            }
        }

        fun getAll(): List<AnomalyHistory> {
            return SQLite.select()
                    .from(AnomalyHistory::class.java)
                    .queryList()
        }

        fun getUpcoming(): List<AnomalyHistory> {
            return SQLite.select()
                    .from(AnomalyHistory::class.java)
                    .where(AnomalyHistory_Table.WINNER.`is`("N/A"))
                    .queryList()
        }

        fun getPast(): List<AnomalyHistory> {
            return SQLite.select()
                    .from(AnomalyHistory::class.java)
                    .where(AnomalyHistory_Table.WINNER.isNot("N/A"))
                    .queryList()
        }
    }
}