package com.optic.ecoapt.models

import com.google.gson.Gson

class Event (

    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val date: String,
    ) {

    override fun toString(): String {
        return "Event(id='$id', name='$name', description='$description', image='$image', date='$date')"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

}

