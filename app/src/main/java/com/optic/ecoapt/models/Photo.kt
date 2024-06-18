package com.optic.ecoapt.models

import com.google.gson.Gson

class Photo (

    val id: String,
    val name: String,
    val image: String,
    ) {


    override fun toString(): String {
        return "Photo(id='$id', name='$name', image='$image')"
    }

    fun toJson(): String{
        return Gson().toJson(this)
    }


}

