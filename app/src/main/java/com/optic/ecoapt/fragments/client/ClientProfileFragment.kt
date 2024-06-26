package com.optic.ecoapt.fragments.client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


class ClientProfileFragment : Fragment() {



    private val TAG = "UserProfileActivity"
    var myView: View? = null
    var circleImageUser: CircleImageView? = null
    var buttonUpdateProfile: Button? = null
    var textViewName: TextView?=null
    var textViewLastname: TextView?=null
    var textViewEmail: TextView?=null
    var imageviewBackmenu: ImageView?= null
    var buttonLogout: Button? = null

    var sharedPref: SharedPref? = null
    var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_client_profile, container, false)

        sharedPref = SharedPref(requireActivity())
        buttonUpdateProfile = myView?.findViewById(R.id.btn_update_profile)
        textViewName = myView?.findViewById(R.id.textview_name)
        textViewLastname = myView?.findViewById(R.id.textview_lastname)
        textViewEmail = myView?.findViewById(R.id.textview_email)
        circleImageUser = myView?.findViewById(R.id.circleimage_user)
        imageviewBackmenu = myView?.findViewById(R.id.imageview_backmenu)
        buttonLogout = myView?.findViewById(R.id.btn_logoutClient)

        buttonUpdateProfile?.setOnClickListener{goToUpdate()}
        buttonLogout?.setOnClickListener{ logout() }

        getUserFromSession()

        textViewName?.text = "${user?.name}"
        textViewLastname?.text = "${user?.lastname}"
        textViewEmail?.text = "${user?.email}"
        //imageviewBackmenu?.setOnClickListener{backmenu()}

        if (!user?.image.isNullOrBlank()) {
            Glide.with(requireContext()).load(user?.image).into(circleImageUser!!)

        }


        return myView
    }


    private fun getUserFromSession(){

//        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }

    private fun  logout() {

        sharedPref?.remove("user")
        val i = Intent(requireContext(), MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // eliminar historial de pantallas
        startActivity(i)
    }

    private fun goToUpdate(){
        val i = Intent(requireContext(), ClientUpdateActivity::class.java)

        startActivity(i)
    }


}