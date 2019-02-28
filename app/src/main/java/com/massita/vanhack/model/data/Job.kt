package com.massita.vanhack.model.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Job(
    @SerializedName("id") var id: Int,
    @SerializedName("positionName") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("company") var company: String,
    @SerializedName("city") var city: String,
    @SerializedName("country") var country: String,
    @SerializedName("postDate") var date: String,
    @SerializedName("mustHaveSkills") var mustHaveSkills: List<Skills>,
    @SerializedName("niceToHaveSkills") var niceToHaveSkills: List<Skills>,
    @SerializedName("jobType") var jobType: String,
    @SerializedName("salaryRangeStart") var salaryRangeStart: String,
    @SerializedName("salaryRangeEnd") var salaryRangeEnd: String,
    @SerializedName("applied") var applied: Boolean,
    @SerializedName("favorited") var favorited: Boolean,
    @SerializedName("newJob") var newJob: Boolean,
    @SerializedName("matchPorcentage") var matchPorcentage: Int
)