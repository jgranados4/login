package com.example.login.data.requests

import com.google.gson.annotations.SerializedName

data  class UsersRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

        )
