package com.optic.ecoapt.providers

import com.optic.ecoapt.api.ApiRoutes
import com.optic.ecoapt.models.Category
import com.optic.ecoapt.routes.CategoriesRoutes
import retrofit2.Call

class CategoriesProvider (val token:String){

        private var categoriesRoutes: CategoriesRoutes? = null

        init {
            val api = ApiRoutes()
            categoriesRoutes = api.getCategoriesRoutes(token)

        }

        fun getAll(): Call<ArrayList<Category>>? {
            return categoriesRoutes?.getAll()
        }


//        fun  create(file: File, reward: Reward): Call<ResponseHttp>? {
//            val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
//            val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
//            val requestBody = RequestBody.create(MediaType.parse("text/plain"), reward.toJson())
//
//            return rewardsRoutes?.create(image, requestBody, token)
//
//        }


}