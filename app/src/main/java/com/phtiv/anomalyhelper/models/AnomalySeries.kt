package com.phtiv.anomalyhelper.models

import com.raizlabs.android.dbflow.annotation.Table
import com.phtiv.anomalyhelper.db.AppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite




@Table(database = AppDatabase::class, name = "AnomalySeries_Table")
class AnomalySeries {
    @PrimaryKey
    var ID: Int = 0
    @Column
    var NAME: String = ""
    @Column
    var ENL_SCORE: String = ""
    @Column
    var RES_SCORE: String = ""
    @Column
    var WINNER: String = ""

    companion object {
        fun insertOrReplace(seriesList: List<AnomalySeries>) {
            val adapter = FlowManager.getModelAdapter<AnomalySeries>(AnomalySeries::class.java)
            seriesList.forEach {
                if (adapter.exists(it))
                    adapter.update(it)
                else
                    adapter.insert(it)
            }
        }

        fun getAll(): List<AnomalySeries> {
            return  SQLite.select()
                    .from(AnomalySeries::class.java)
                    .queryList()
        }

        fun getUpcoming(): List<AnomalySeries> {
            return SQLite.select()
                    .from(AnomalySeries::class.java)
                    .where(AnomalySeries_Table.WINNER.`is`("N/A"))
                    .queryList()
        }

        fun getPast(): List<AnomalySeries> {
            return SQLite.select()
                    .from(AnomalySeries::class.java)
                    .where(AnomalySeries_Table.WINNER.isNot("N/A"))
                    .queryList()
        }
    }
}
