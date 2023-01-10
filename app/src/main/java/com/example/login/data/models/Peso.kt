package com.example.login.data.models

import com.google.gson.annotations.SerializedName

data  class Peso (
    @SerializedName("id")
    var id: Int,

    @SerializedName("peso")
    var peso: String,


    )