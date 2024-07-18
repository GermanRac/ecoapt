package com.optic.ecoapt.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.fragments.client.ClientCategoriesFragment
import com.optic.ecoapt.fragments.client.ClientEcocomparteFragment
import com.optic.ecoapt.fragments.client.ClientProfileFragment
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

        openFragment(ClientRewardFragment())
//        openFragment(ClientCategoriesFragment())
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
//                    openFragment(ClientRewardFragment())
//                    true

                    openFragment(ClientCategoriesFragment())
                    true
                }

                R.id.item_profile ->{
                    openFragment(ClientProfileFragment())
                    true
                }

                else -> false

            }
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





}