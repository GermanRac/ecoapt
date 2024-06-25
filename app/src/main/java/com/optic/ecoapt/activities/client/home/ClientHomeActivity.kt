package com.optic.ecoapt.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.activities.MainActivity
import com.optic.ecoapt.fragments.client.ClientEcocomparteFragment
import com.optic.ecoapt.fragments.client.ClientRewardFragment
import com.optic.ecoapt.fragments.client.ClientScheduleFragment
import com.optic.ecoapt.models.User
import com.optic.ecoapt.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"

    var sharedPref: SharedPref? = null
    var bottomNavigation:BottomNavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        sharedPref = SharedPref(this)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation?.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.item_schedule ->{
                    openFragment(ClientScheduleFragment())
                    true
                }

                R.id.item_ecocomparte ->{
                    openFragment(ClientEcocomparteFragment())
                    true
                }

                R.id.item_rewards ->{
                    openFragment(ClientRewardFragment())
                    true
                }

                else -> false

            }
        }

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

    private fun openFragment (fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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