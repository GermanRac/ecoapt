package com.optic.ecoapt.routes

import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.Reward
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RewardsRoutes {



    @GET("photos/getAll")
    fun getAll():Call<ArrayList<Reward>>


    @Multipart
    @POST("photos/create")
    fun create(
        @Part image: MultipartBody.Part,
        @Part("photo") photo: RequestBody,
        @Header("Authorization") token:String
    ): Call<ResponseHttp>



}



