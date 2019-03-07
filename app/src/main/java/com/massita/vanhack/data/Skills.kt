package com.massita.vanhack.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Skills(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Skills> {
        override fun createFromParcel(parcel: Parcel): Skills {
            return Skills(parcel)
        }

        override fun newArray(size: Int): Array<Skills?> {
            return arrayOfNulls(size)
        }
    }
}