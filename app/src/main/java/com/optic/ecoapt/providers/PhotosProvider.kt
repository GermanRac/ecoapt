package com.optic.ecoapt.providers

import com.optic.ecoapt.api.ApiRoutes
import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.routes.PhotosRoutes

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class PhotosProvider(val token:String) {

    private var photosRoutes:PhotosRoutes? = null

    init {
        val api =ApiRoutes()
        photosRoutes = api.getPhotosRoutes(token)

    }

    fun getAll():Call<ArrayList<Photo>>? {
        return photosRoutes?.getAll()
    }


    fun  create(file: File, photo: Photo): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), photo.toJson())

        return photosRoutes?.create(image, requestBody, token)
    }


}