package com.optic.ecoapt.providers

import com.optic.ecoapt.api.ApiRoutes
import com.optic.ecoapt.models.Category
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.Reward
import com.optic.ecoapt.routes.RewardsRoutes

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class RewardsProvider(val token:String) {

    private var rewardsRoutes:RewardsRoutes? = null

    init {
        val api =ApiRoutes()
        rewardsRoutes = api.getRewardsRoutes(token)

    }

//    fun getAll():Call<ArrayList<Reward>>? {
//        return rewardsRoutes?.getAll()
//    }


    fun findByCategory(idCategory: String):Call<ArrayList<Reward>>? {
        return rewardsRoutes?.findByCategory(idCategory)
    }

    fun  create(file: File, reward: Reward): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), reward.toJson())

        return rewardsRoutes?.create(image, requestBody, token)
    }


}