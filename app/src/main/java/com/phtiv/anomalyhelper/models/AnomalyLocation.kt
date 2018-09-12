package com.phtiv.anomalyhelper.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class AnomalyLocation(
        @SerializedName("ID") val ID: String = "",
        @SerializedName("NAME") val NAME: String = "",
        @SerializedName("CITY") val CITY: String = "",
        @SerializedName("SUBLOCATION") val SUBLOCATION: String = "",
        @SerializedName("LAT") val LAT: String = "",
        @SerializedName("LON") val LON: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ID)
        parcel.writeString(NAME)
        parcel.writeString(CITY)
        parcel.writeString(SUBLOCATION)
        parcel.writeString(LAT)
        parcel.writeString(LON)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnomalyLocation> {
        override fun createFromParcel(parcel: Parcel): AnomalyLocation {
            return AnomalyLocation(parcel)
        }

        override fun newArray(size: Int): Array<AnomalyLocation?> {
            return arrayOfNulls(size)
        }
    }


}