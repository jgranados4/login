package com.example.login.data.services

import com.example.login.data.requests.PesoRequest
import com.example.login.data.requests.UsersRequest
import com.example.login.data.responses.DefaultReponse
import com.example.login.data.responses.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService  {
    @Headers("Accept:application/json", "Content-Type:application/json")
    @GET("/")
    fun getListEmployees(): Call<UserResponse>
    @POST("/add_peso")
    fun addPeso(@Body request: PesoRequest): Call<DefaultReponse>
    @POST("/add_login")
    fun addEmployee(@Body request: UsersRequest): Call<DefaultReponse>
    @POST("/auth")
    fun Auth(@Body request: UsersRequest): Call<DefaultReponse>
//
    @PUT("/update_login/{id}")
    fun updateEmployee(@Path("id") id: Int, @Body request: UsersRequest): Call<DefaultReponse>
    @DELETE("/delete_login/{emp_id}")
    fun deleteEmployee(@Path("emp_id") emp_id: Int): Call<DefaultReponse>
}