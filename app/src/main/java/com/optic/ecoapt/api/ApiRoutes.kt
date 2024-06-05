package com.optic.ecoapt.api

import com.optic.ecoapt.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.56.1:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes():UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}