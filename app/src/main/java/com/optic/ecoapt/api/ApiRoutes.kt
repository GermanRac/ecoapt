package com.optic.ecoapt.api

import com.optic.ecoapt.routes.EventsRoutes
import com.optic.ecoapt.routes.PhotosRoutes
import com.optic.ecoapt.routes.RewardsRoutes
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

    fun getPhotosRoutes(token:String):PhotosRoutes {
        return retrofit.getClientWithToken(API_URL,token).create(PhotosRoutes::class.java)
    }

    fun getRewardsRoutes(token:String):RewardsRoutes {
        return retrofit.getClientWithToken(API_URL,token).create(RewardsRoutes::class.java)
    }

}