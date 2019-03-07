package com.massita.vanhack.presentation.data

import com.google.gson.annotations.SerializedName

data class JobSearchResult(
    @SerializedName("totalQuery") var totalQuery: Int,
    @SerializedName("totalCount") var totalCount: Int,
    @SerializedName("items") var jobs: List<Job>
)