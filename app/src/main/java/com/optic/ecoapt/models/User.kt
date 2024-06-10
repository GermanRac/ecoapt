package com.optic.ecoapt.models

import android.widget.CheckBox
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") val id:String? = null,
    @SerializedName("email") val email:String,
    @SerializedName("name") val name:String,
    @SerializedName("lastname") val lastname:String,
    @SerializedName("points") val points:Int? = null,
//    @SerializedName("consent") val consent: CheckBox?,
    @SerializedName("password") val password:String,
    @SerializedName("session_token") val sessionToken:String? = null,
    @SerializedName("image") val image:String? = null,


    ) {
    override fun toString(): String {
        return "User(id='$id', email='$email', name='$name', lastname='$lastname', points=$points, password='$password', session_token='$sessionToken',image='$image')"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}