package com.optic.ecoapt.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.activities.MainActivity
import com.optic.ecoapt.models.User
import com.optic.ecoapt.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"
    var buttonLogout: Button? = null
    var sharedPref: SharedPref? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        sharedPref = SharedPref(this)

        buttonLogout = findViewById(R.id.btn_logoutClient)
        buttonLogout?.setOnClickListener{ logout() }
        //buttonRewards = findViewById(R.id.btn_rewards)
        //buttonRewards?.setOnClickListener{goToRewards()}

        val cvEcocomparte = findViewById<CardView>(R.id.btn_ecocompartePrincipal)
        cvEcocomparte.setOnClickListener {
            goToEcocomparte()
        }

        val cvSchedule = findViewById<CardView>(R.id.btn_schedulePrincipal)
        cvSchedule.setOnClickListener {
            goToSchedule()
        }

        val btnProfile = findViewById<ImageButton>(R.id.btn_profile)
        btnProfile.setOnClickListener {
            goToProfile()
        }

        getUserFromSession()
    }



    private fun  logout() {

        sharedPref?.remove("user")
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }

    private fun goToProfile() {
        val i = Intent(this, UserProfileActivity::class.java)
        startActivity(i)
    }

    private fun goToEcocomparte() {
        val i = Intent(this, EcocomparteActivity::class.java)
        startActivity(i)
    }

    private fun goToSchedule() {
        val i = Intent(this, ScheduleActivity::class.java)
        startActivity(i)
    }

//    private fun goToGame() {
//        val i = Intent(this, GameActivity::class.java)
//        startActivity(i)
//    }

//    private fun goToEcocomparte() {
//        val i = Intent(this, EcocomparteActivity::class.java)
//        startActivity(i)
//    }


}