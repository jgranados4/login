package com.example.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.login.R
import com.example.login.data.services.ApiClient

class VentanaNew : AppCompatActivity() {
    lateinit var txtPeso: EditText
    lateinit var btnAdd: Button
    lateinit var btnBack: Button
    lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_new)
    }
    fun regresar(view: View) {
        finish()
        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
    }
}