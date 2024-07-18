package com.optic.ecoapt.models

class Category (

    val id: String,
    val name: String,
    val image: String

) {
    override fun toString(): String {
        return "Category(id='$id', name='$name', image='$image')"
    }
}