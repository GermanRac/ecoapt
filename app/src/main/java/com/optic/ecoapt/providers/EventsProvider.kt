package com.optic.ecoapt.providers

import com.optic.ecoapt.api.ApiRoutes
import com.optic.ecoapt.models.Event
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.routes.EventsRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class EventsProvider {

    private var eventsRoutes:EventsRoutes? = null

    init {
        val api =ApiRoutes()
        eventsRoutes = api.getEventsRoutes()

    }

    fun getAll():Call<String>? {
        return eventsRoutes?.getAll()
    }


    fun  create(file: File, event: Event): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"),file)
        val image = MultipartBody.Part.createFormData("image",file.name,reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), event.toJson())

        return eventsRoutes?.create(image, requestBody)
    }


}