package com.optic.ecoapt.models
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
class Reward (
     val id: String,
     val name: String,
     val description:String,
     val points: String,
     val quantity: String,
     val image_url: String,

    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Reward(id='$id', name='$name', description='$description', points='$points', quantity='$quantity', image_url='$image_url')"
    }


}
