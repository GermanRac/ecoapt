package com.optic.ecoapt.providers

import com.optic.ecoapt.api.ApiRoutes
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {
    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()

    }

    fun register(user:User):Call<ResponseHttp>? {
        return usersRoutes?.register(user)

    }

    fun login(email:String,password:String):Call<ResponseHttp>?{
        return usersRoutes?.login(email,password )
    }

}