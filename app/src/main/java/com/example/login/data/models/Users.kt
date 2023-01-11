package com.example.login.data.models

import com.google.gson.annotations.SerializedName

data  class Users (
    @SerializedName("id")
    var id: Int,
    @SerializedName("Fullname")
    var Fullname: String,

    @SerializedName("Email")
    var Email: String,
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,


    )
