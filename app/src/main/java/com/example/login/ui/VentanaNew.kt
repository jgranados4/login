package com.example.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.login.R
import com.example.login.data.requests.PesoRequest
import com.example.login.data.requests.UsersRequest
import com.example.login.data.responses.DefaultReponse
import com.example.login.data.services.ApiClient
import com.example.login.util.MyMessages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VentanaNew : AppCompatActivity() {
    lateinit var txtPeso: EditText
    lateinit var btnAdd: Button
    lateinit var btnBack: Button
    lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_new)

        apiClient=ApiClient()
        txtPeso=findViewById(R.id.txtPeso)
        btnAdd=findViewById(R.id.btnGuardar)
        btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AddPeso()
            }

        }
    }

    private suspend fun AddPeso() {
        val Peso = txtPeso.text.toString().trim()

        if (Peso.isEmpty()) {
            withContext(Dispatchers.Main) {
                txtPeso.error = "Ingrese el Username"
                txtPeso.requestFocus()
            }
            return
        }

        apiClient.getApiService(this).addPeso(PesoRequest(Peso ))
            .enqueue(object: Callback<DefaultReponse> {
                override fun onResponse(
                    call: Call<DefaultReponse>,
                    response: Response<DefaultReponse>
                ) {
                    val defaultResponse=response.body()
                    if ( defaultResponse!= null) {
                        if (response.code()==200 && defaultResponse.error==false){
                            MyMessages.toast(applicationContext,defaultResponse.message)
                            val Ventanaw= Intent(this@VentanaNew, MainActivity::class.java).
                            putExtra("Nombre","juan")

                            startActivity(Ventanaw)

                            return
                        }
                    }else{
                        MyMessages.toast(applicationContext,"Error")
                    }
                }

                override fun onFailure(call: Call<DefaultReponse>, t: Throwable) {
                    MyMessages.toast(applicationContext,t.toString())
                }

            })
    }
    fun regresar(view: View) {
        finish()
        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
    }

}

