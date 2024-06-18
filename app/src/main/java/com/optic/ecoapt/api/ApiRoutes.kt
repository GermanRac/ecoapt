package com.optic.ecoapt.api

import com.optic.ecoapt.routes.EventsRoutes
import com.optic.ecoapt.routes.PhotosRoutes
import com.optic.ecoapt.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.56.1:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes():UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getUsersRoutesWithToken(token:String):UsersRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(UsersRoutes::class.java)
    }

    fun getEventsRoutes():EventsRoutes {
        return retrofit.getClient(API_URL).create(EventsRoutes::class.java)
    }

    fun getPhotosRoutes():PhotosRoutes {
        return retrofit.getClient(API_URL).create(PhotosRoutes::class.java)
    }
}