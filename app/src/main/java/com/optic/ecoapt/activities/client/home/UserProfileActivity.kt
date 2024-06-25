package com.optic.ecoapt.activities.client.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.activities.MainActivity
import com.optic.ecoapt.activities.client.update.ClientUpdateActivity
import com.optic.ecoapt.models.User
import com.optic.ecoapt.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView

class UserProfileActivity : AppCompatActivity() {

    private val TAG = "UserProfileActivity"
    var circleImageUser: CircleImageView? = null
    var buttonUpdateProfile: Button? = null
    var textViewName:TextView?=null
    var textViewLastname:TextView?=null
    var textViewEmail:TextView?=null
    var imageviewBackmenu:ImageView?= null
    var buttonLogout: Button? = null


    var sharedPref: SharedPref? = null
    var user:User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        buttonUpdateProfile = findViewById(R.id.btn_update_profile)
        textViewName = findViewById(R.id.textview_name)
        textViewLastname = findViewById(R.id.textview_lastname)
        textViewEmail = findViewById(R.id.textview_email)
        circleImageUser = findViewById(R.id.circleimage_user)
        imageviewBackmenu = findViewById(R.id.imageview_backmenu)
        buttonLogout = findViewById(R.id.btn_logoutClient)

        sharedPref = SharedPref(this)
        buttonUpdateProfile?.setOnClickListener{goToUpdate()}

        buttonLogout?.setOnClickListener{ logout() }

        getUserFromSession()

        textViewName?.text = "${user?.name}"
        textViewLastname?.text = "${user?.lastname}"
        textViewEmail?.text = "${user?.email}"
        imageviewBackmenu?.setOnClickListener{backmenu()}

        if (!user?.image.isNullOrBlank()) {
            Glide.with(this).load(user?.image).into(circleImageUser!!)

        }




    }


    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }

    private fun  logout() {

        sharedPref?.remove("user")
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    private fun goToUpdate(){
        val i = Intent(this, ClientUpdateActivity::class.java)
        startActivity(i)
    }


    private fun  backmenu() {
        val i = Intent(this, ClientHomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // eliminar historial de pantallas
        startActivity(i)
    }


}