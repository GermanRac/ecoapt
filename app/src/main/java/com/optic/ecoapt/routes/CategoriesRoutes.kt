package com.optic.ecoapt.routes

import com.optic.ecoapt.models.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesRoutes {



    @GET("categories/getAll")
    fun getAll():Call<ArrayList<Category>>






}



