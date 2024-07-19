package com.optic.ecoapt.fragments.client

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


class ClientEcocomparteFragment : Fragment() {

    private val TAG = "EcocomparteActivity"
    var myView: View? = null
    var recyclerViewPhotos: RecyclerView? = null
    var photosProvider: PhotosProvider? = null
    var adapter: PhotosAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var photos = ArrayList<Photo>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_ecocomparte, container, false)

        recyclerViewPhotos = myView?.findViewById(R.id.recyclerview_photos)
        recyclerViewPhotos?.layoutManager = LinearLayoutManager(requireContext()) // ELEMENTOS SE MOSTRARAN DE MANERA VERTICAL

        sharedPref = SharedPref(requireActivity())
        getUserFromSession()
//        photosProvider = PhotosProvider(user?.sessionToken!!)
        user?.sessionToken?.let { token ->
            photosProvider = PhotosProvider(token)
        }

        getPhotos()

        val btnUpload= myView?.findViewById<Button>(R.id.btn_UploadImage)
        btnUpload?.setOnClickListener{ goToUpdatePhoto() }

        return myView
    }





    private fun goToUpdatePhoto() {
        val fragment = ClientEcocomparteUpdateFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getPhotos() {
        photosProvider?.getAll()?.enqueue(object : Callback<ArrayList<Photo>> {
            override fun onResponse(call: Call<ArrayList<Photo>>, response: Response<ArrayList<Photo>>) {
                if (response.body() != null) {
                    photos.addAll(response.body()!!)
                    adapter = PhotosAdapter(requireActivity(), photos)
                    recyclerViewPhotos?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }



//    private fun getUserFromSession(){
//
//        val sharedPref = SharedPref(requireActivity())
//        val gson = Gson()
//
//        if (!sharedPref.getData("user").isNullOrBlank()) {
//            //si el usuario existe en sesion
//            val user = gson.fromJson(sharedPref.getData("user"), User::class.java )
//            Log.d(TAG,"Usuario: $user")
//        }
//    }

    private fun getUserFromSession() {
        val gson = Gson()
        val userData = sharedPref?.getData("user")
        if (!userData.isNullOrBlank()) {
            user = gson.fromJson(userData, User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }




}