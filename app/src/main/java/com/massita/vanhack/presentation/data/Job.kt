package com.massita.vanhack.presentation.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Job(
    @SerializedName("id") var id: Int,
    @SerializedName("positionName") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("company") var company: String?,
    @SerializedName("city") var city: String?,
    @SerializedName("country") var country: String?,
    @SerializedName("postDate") var date: Date,
    @SerializedName("mustHaveSkills") var mustHaveSkills: List<Skills>?,
    @SerializedName("niceToHaveSkills") var niceToHaveSkills: List<Skills>?,
    @SerializedName("jobType") var jobType: String?,
    @SerializedName("salaryRangeStart") var salaryRangeStart: String?,
    @SerializedName("salaryRangeEnd") var salaryRangeEnd: String?,
    @SerializedName("applied") var applied: Boolean,
    @SerializedName("favorited") var favorited: Boolean,
    @SerializedName("newJob") var newJob: Boolean,
    @SerializedName("matchPorcentage") var matchPorcentage: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        Date(parcel.readLong()),
        parcel.createTypedArrayList(Skills),
        parcel.createTypedArrayList(Skills),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(company)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeLong(date.time)
        parcel.writeTypedList(mustHaveSkills)
        parcel.writeTypedList(niceToHaveSkills)
        parcel.writeString(jobType)
        parcel.writeString(salaryRangeStart)
        parcel.writeString(salaryRangeEnd)
        parcel.writeByte(if (applied) 1 else 0)
        parcel.writeByte(if (favorited) 1 else 0)
        parcel.writeByte(if (newJob) 1 else 0)
        parcel.writeInt(matchPorcentage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Job> {
        override fun createFromParcel(parcel: Parcel): Job {
            return Job(parcel)
        }

        override fun newArray(size: Int): Array<Job?> {
            return arrayOfNulls(size)
        }
    }
}