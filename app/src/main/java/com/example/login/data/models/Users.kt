package com.example.login.data.models

import com.google.gson.annotations.SerializedName

data  class Users (
    @SerializedName("id")
    var id: Int,

    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,


    )
