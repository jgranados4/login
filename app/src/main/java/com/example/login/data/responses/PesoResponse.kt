package com.example.login.data.responses

import com.example.login.data.models.Peso
import com.google.gson.annotations.SerializedName

data class PesoResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("peso")
    var peso: List<Peso>
)