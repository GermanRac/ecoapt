package com.optic.ecoapt.models

import com.google.gson.Gson

class Category (

    val id: String,
    val name: String,
    val image: String,

) {

    fun toJson(): String{
        return Gson().toJson(this)
    }



    override fun toString(): String {
        return "Category(id='$id', name='$name', image='$image')"
    }
}