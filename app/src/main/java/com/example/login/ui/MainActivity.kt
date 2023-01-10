package com.example.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.login.R
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

class MainActivity : AppCompatActivity() {
    lateinit var TxtUsername: EditText
    lateinit var TxtPassword: EditText
    lateinit var BtnLogin: Button
    lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiClient = ApiClient()
        TxtUsername = findViewById(R.id.Username)
        TxtPassword = findViewById(R.id.Password)
        BtnLogin = findViewById(R.id.Login)

        BtnLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Addlogin()
            }

        }
    }

    private suspend fun Addlogin() {
        val Username = TxtUsername.text.toString().trim()
        val Password = TxtPassword.text.toString().trim()

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
        apiClient.getApiService(this).Auth(UsersRequest(Username, Password ))
            .enqueue(object: Callback<DefaultReponse> {
                override fun onResponse(
                    call: Call<DefaultReponse>,
                    response: Response<DefaultReponse>
                ) {
                    val defaultResponse=response.body()
                    if ( defaultResponse!= null) {
                        if (response.code()==200 && defaultResponse.error==false){
                            MyMessages.toast(applicationContext,defaultResponse.message)
                            val Ventanaw= Intent(this@MainActivity, VentanaNew::class.java).
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

}