package com.massita.vanhack.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Skills(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
) : Serializable