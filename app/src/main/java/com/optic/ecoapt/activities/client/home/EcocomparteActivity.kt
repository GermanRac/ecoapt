package com.optic.ecoapt.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.models.User
import com.optic.ecoapt.utils.SharedPref

class EcocomparteActivity : AppCompatActivity() {

    private val TAG1 = "EcocomparteActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecocomparte)


        val btnIcoAtras = findViewById<ImageButton>(R.id.btnIcoAtras)
        btnIcoAtras.setOnClickListener {
            onBackPressed()
        }

        getUserFromSession()
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish() // Finaliza la actividad actual
    }


    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java )
            Log.d(TAG1,"Usuario: $user")
        }
    }


}