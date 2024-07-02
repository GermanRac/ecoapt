package com.optic.ecoapt.models
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
class Reward (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description:String,
    @SerializedName("points") val points: String,
    @SerializedName("image") val image: String,

    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Reward(id='$id', name='$name', description=$description, points='$points', image='$image')"
    }


}
