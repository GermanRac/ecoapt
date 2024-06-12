package com.optic.ecoapt.models
import com.google.gson.annotations.SerializedName
class Reward (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,

    ){

    override fun toString(): String {
        return "Reward(id='$id', name='$name', image='$image')"
    }
}
