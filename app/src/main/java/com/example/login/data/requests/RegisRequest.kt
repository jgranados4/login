package com.example.login.data.requests

import com.google.gson.annotations.SerializedName

data  class RegisRequest (
    @SerializedName("Fullname")
    var Fullname: String,

    @SerializedName("Email")
    var Email: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

        )
