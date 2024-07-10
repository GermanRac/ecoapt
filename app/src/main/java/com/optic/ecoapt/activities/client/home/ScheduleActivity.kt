package com.optic.ecoapt.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.adapters.EventsAdapter
import com.optic.ecoapt.models.Event
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.EventsProvider
import com.optic.ecoapt.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleActivity : AppCompatActivity() {

    private val TAG = "ScheduleActivity"

    var recyclerViewEvents: RecyclerView? = null
    var eventsProvider: EventsProvider? = null
    var adapter: EventsAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var events = ArrayList<Event>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


        recyclerViewEvents = findViewById(R.id.recyclerview_events)
        recyclerViewEvents?.layoutManager = LinearLayoutManager(this) // ELEMENTOS SE MOSTRARAN DE MANERA VERTICAL

        sharedPref = SharedPref(this)
        getUserFromSession()
        eventsProvider = EventsProvider()
        getEvents()


        val btnIcoAtras = findViewById<ImageButton>(R.id.btnIcoAtras)
        btnIcoAtras.setOnClickListener {
            onBackPressed()
        }

    }


    private fun getEvents() {
        eventsProvider?.getAll()?.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.body() != null) {
                    val jsonString = response.body()
                    val objectMapper = ObjectMapper()
                    try {
                        val rootNode: JsonNode = objectMapper.readTree(jsonString)
                        val events = mutableMapOf<String, Event>()

                        rootNode.fields().forEach { entry ->
                            val key = entry.key
                            val eventNode = entry.value

                            val description = eventNode["description"].asText()
                            val eventDate = eventNode["event_date"].asText()
                            val title = eventNode["title"].asText()

                            val event = Event(key,description, eventDate,"", title)
                            events[key] = event
                        }

                        events.forEach { (key, event) ->
                            println("Key: $key, Event: $event")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(this@ScheduleActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish() // Finaliza la actividad actual
    }


    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }






}