package com.optic.ecoapt.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.adapters.PhotosAdapter
import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.PhotosProvider
import com.optic.ecoapt.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EcocomparteActivity : AppCompatActivity() {

    private val TAG = "EcocomparteActivity"
    var recyclerViewPhotos: RecyclerView? = null
    var photosProvider: PhotosProvider? = null
    var adapter: PhotosAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var photos = ArrayList<Photo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecocomparte)

        recyclerViewPhotos = findViewById(R.id.recyclerview_photos)
        recyclerViewPhotos?.layoutManager = LinearLayoutManager(this) // ELEMENTOS SE MOSTRARAN DE MANERA VERTICAL

        sharedPref = SharedPref(this)
        getUserFromSession()
        photosProvider = PhotosProvider(user?.sessionToken!!)
        getPhotos()



        val btnIcoAtras = findViewById<ImageButton>(R.id.btnIcoAtras)
        btnIcoAtras.setOnClickListener {
            onBackPressed()
        }


        val btnUpload= findViewById<Button>(R.id.btn_UploadImage)
        btnUpload.setOnClickListener{

        }





    }




    private fun getPhotos() {
        photosProvider?.getAll()?.enqueue(object : Callback<ArrayList<Photo>> {
            override fun onResponse(call: Call<ArrayList<Photo>>, response: Response<ArrayList<Photo>>) {
                if (response.body() != null) {
                    photos.addAll(response.body()!!)
                    adapter = PhotosAdapter(this@EcocomparteActivity, photos)
                    recyclerViewPhotos?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(this@EcocomparteActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
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