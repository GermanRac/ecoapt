package com.optic.ecoapt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import com.optic.ecoapt.R

class RegisterActivity : AppCompatActivity() {

    val TAG ="RegisterActivity"

    var imageViewToLogin: ImageView? = null
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var buttonRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        imageViewToLogin = findViewById(R.id.image_go_login)
        editTextName= findViewById(R.id.edittext_name)
        editTextLastName= findViewById(R.id.edittext_lastname)
        editTextEmail= findViewById(R.id.edittext_email)
        editTextPassword= findViewById(R.id.edittext_password)
        editTextConfirmPassword= findViewById(R.id.edittext_confirm_password)
        buttonRegister= findViewById(R.id.btn_register)


        imageViewToLogin?.setOnClickListener { goToLogin() }
        buttonRegister?.setOnClickListener{ register() }
    }

    private fun register() {
        val name = editTextName?.text.toString()
        val lastname = editTextLastName?.text.toString()
        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()
        val confirmPassword = editTextConfirmPassword?.text.toString()


        Log.d(TAG,"El nombre es: $name ")
        Log.d(TAG,"El apellido es: $lastname ")
        Log.d(TAG,"El email es: $email ")
        Log.d(TAG,"El password es: $password ")
        Log.d(TAG,"El confirm es: $confirmPassword ")
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}