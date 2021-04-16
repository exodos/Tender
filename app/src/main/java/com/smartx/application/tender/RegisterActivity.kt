package com.smartx.application.tender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
//    private lateinit var txtLogin: TextView
    private lateinit var userName: TextView
    private lateinit var email:TextView
    private lateinit var password:TextView
    private lateinit var register:Button

    val queue = Volley.newRequestQueue(this)
    val url = "localhost/android/register.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        txtLogin = findViewById(R.id.txtLogin);
//
//        txtLogin.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }

        userName = findViewById(R.id.userName)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        register = findViewById(R.id.btnRegister)



        register.setOnClickListener {
            val sUserName = userName.text.toString().trim()
            val sEmail = email.text.toString().trim()
            val sPassword = password.text.toString().trim()

            if (sUserName.isNotEmpty() || sEmail.isNotEmpty() || sPassword.isNotEmpty()){
                registerUser(sUserName, sEmail, sPassword)
            } else {
                userName.error = "Please Insert User Name"
                email.error = "Please Insert Email"
                password.error = "Please Insert Password"
            }
        }

    }

    private fun registerUser(sUserName: String, sEmail: String, sPassword: String) {

        val stringRequest = StringRequest(Request.Method.POST, url,
                { response ->
                    try {
                        val obj = JSONObject(response)

                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    }catch (e : JSONException){
                        e.printStackTrace()
                    }
                    // Display the first 500 characters of the response string.
//                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                {  })







// Add the request to the RequestQueue.
        queue.add(stringRequest)


    }
}