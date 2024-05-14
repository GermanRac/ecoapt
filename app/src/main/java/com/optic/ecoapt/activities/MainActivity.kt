package com.optic.ecoapt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.optic.ecoapt.R

class MainActivity : AppCompatActivity() {

    var imageViewGoToRegister: Button? = null
    var editTextEmail:EditText? = null
    var editTextPassword:EditText? = null
    var buttonLogin:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.btnRegister)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonLogin = findViewById(R.id.btn_login)


        imageViewGoToRegister?.setOnClickListener { goToRegister() }
        buttonLogin?.setOnClickListener{login()}
    }

    private fun login(){
        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()

        Toast.makeText(this,"El Email es:${email} ",Toast.LENGTH_LONG).show()
        Toast.makeText(this,"El Password es:${password} ",Toast.LENGTH_LONG).show()

        Log.d("MainActivity","El mail es : $email")
        Log.d("MainActivity","El mail es : $password")

    }


    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }


}