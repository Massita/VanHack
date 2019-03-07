package com.massita.vanhack.data

import com.google.gson.annotations.SerializedName

data class JobsResponse(
    @SerializedName("result") var searchResult: JobSearchResult
)