package com.massita.vanhack.presentation.data

import com.google.gson.annotations.SerializedName

data class JobsResponse(
    @SerializedName("result") var searchResult: JobSearchResult
)