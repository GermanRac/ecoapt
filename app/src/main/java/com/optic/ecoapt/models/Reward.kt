package com.optic.ecoapt.models
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
class Reward (
     val id: String,
     val name: String,
     val description:String,
     val reward_points: String,
     val image: String,

    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Reward(id='$id', name='$name', description='$description', reward_points='$reward_points', image='$image')"
    }


}
