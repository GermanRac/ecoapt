package com.optic.ecoapt.models

import android.widget.CheckBox
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") val id:String? = null,
    @SerializedName("email") val email:String,
    @SerializedName("name") var name:String,
    @SerializedName("lastname") var lastname:String,
    @SerializedName("points") val points:Int? = null,
//    @SerializedName("consent") val consent: CheckBox?,
    @SerializedName("password") val password:String,
    @SerializedName("session_token") val sessionToken:String? = null,
    @SerializedName("image") var image: String? = null


    )




{

    override fun toString(): String {
        return "User(id=$id, email='$email', name='$name', lastname='$lastname', points=$points, password='$password', sessionToken=$sessionToken, image=$image)"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }


}