package com.optic.ecoapt.models
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
class Reward (
     val id: String,
     val name: String,
     val description:String,
     val rewardpoints: String,
     val image: String,

    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Reward(id='$id', name='$name', description='$description', rewardpoints='$rewardpoints', image='$image')"
    }


}
