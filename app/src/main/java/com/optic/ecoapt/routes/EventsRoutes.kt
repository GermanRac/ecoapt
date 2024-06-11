package com.optic.ecoapt.routes

import com.optic.ecoapt.models.Event
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface EventsRoutes {

    @Multipart
    @POST("categories/create")
    fun create(
        @Part image: MultipartBody.Part,
        @Part("event") event: RequestBody
//        @Header("Authorization") token:String
    ): Call<ResponseHttp>



}



