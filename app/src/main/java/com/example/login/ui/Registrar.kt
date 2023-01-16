package com.example.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.login.R
import com.example.login.data.requests.RegisRequest
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

class Registrar : AppCompatActivity() {
    lateinit var TxtFullName: EditText
    lateinit var TxtEmail:EditText
    lateinit var TxtUsername: EditText
    lateinit var TxtPassword: EditText
    lateinit var BtnRegistrar: Button
    lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        apiClient = ApiClient()
        TxtFullName=findViewById(R.id.Rname)
        TxtEmail=findViewById(R.id.Remail)
        TxtUsername=findViewById(R.id.Reusername)
        TxtPassword=findViewById(R.id.Repassword)
        BtnRegistrar=findViewById(R.id.Registrar)
        BtnRegistrar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                AddRegistrado()
            }
        }
    }
    private suspend fun AddRegistrado() {
        val FullName=TxtFullName.text.toString().trim()
        val Email=TxtEmail.text.toString().trim()
        val Username = TxtUsername.text.toString().trim()
        val Password = TxtPassword.text.toString().trim()
        if (FullName.isEmpty()) {
            withContext(Dispatchers.Main) {
                TxtFullName.error = "Ingrese el Full Name"
                TxtFullName.requestFocus()
            }
            return
        }
        if (Email.isEmpty()) {
            withContext(Dispatchers.Main) {
                TxtEmail.error = "Ingrese el Email"
                TxtEmail.requestFocus()
            }
            return
        }
        if (Username.isEmpty()) {
            withContext(Dispatchers.Main) {
                TxtUsername.error = "Ingrese el Username"
                TxtUsername.requestFocus()
            }
            return
        }
        if (Password.isEmpty()) {
            withContext(Dispatchers.Main) {
                TxtPassword.error = "Ingrese el Password"
                TxtPassword.requestFocus()
            }
            return
        }
        apiClient.getApiService(this).addEmployee(RegisRequest(FullName,Email,Username, Password ))
            .enqueue(object: Callback<DefaultReponse> {
                override fun onResponse(
                    call: Call<DefaultReponse>,
                    response: Response<DefaultReponse>
                ) {
                    val defaultResponse=response.body()
                    if ( defaultResponse!= null) {
                        if (response.code()==200 && defaultResponse.error==false){
                            MyMessages.toast(applicationContext,defaultResponse.message)
                            val Ventanaw= Intent(this@Registrar, VentanaNew::class.java).
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