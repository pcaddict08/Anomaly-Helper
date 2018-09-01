package com.phtiv.anomalyhelper

import org.json.JSONObject
import org.greenrobot.greendao.converter.PropertyConverter
import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.phtiv.anomalyhelper.models.DaoSession


class GreenDAOHelper {
    val daoSession: DaoSession? = null
    //val daoMaster: DaoMaster
    //private val db: SQLiteDatabase

    init {
//        val appContext = Application.getInstance().getApplicationContext()
//        val devOpenHelper = DaoMaster.DevOpenHelper(appContext, "media_db", null)
//        db = devOpenHelper.getWritableDatabase()
//        daoMaster = DaoMaster(db)
//        daoSession = daoMaster.newSession()
    }

    class JsonObjectConverter : PropertyConverter<JSONObject, String> {
        override fun convertToEntityProperty(databaseValue: String?): JSONObject? {
            if (databaseValue == null) {
                return null
            }
            try {
                return JSONObject(databaseValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        override fun convertToDatabaseValue(entityProperty: JSONObject?): String? {
            return entityProperty?.toString()
        }
    }

    companion object {

        private var instance: GreenDAOHelper? = null

        @Synchronized
        fun getInstance(): GreenDAOHelper {
            if (instance == null) {
                instance = GreenDAOHelper()
            }
            return instance as GreenDAOHelper
        }
    }
}
