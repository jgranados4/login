package com.example.login.data.responses

import com.example.login.data.models.Users
import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("usuario")
    var usuario: List<Users>
        )