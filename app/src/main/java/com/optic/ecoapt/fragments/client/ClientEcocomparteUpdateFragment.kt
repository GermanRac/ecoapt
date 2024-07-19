package com.optic.ecoapt.fragments.client

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.PhotosProvider
import com.optic.ecoapt.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ClientEcocomparteUpdateFragment : Fragment() {

    var myView: View? = null
    var TAG = "ClientEcocomparteUpdateFragment"
    var imageViewEcoUpdate: ImageView? = null
    var editTextEcoUpdate: EditText? = null
    var buttonUpload: Button? = null

    private  var imageFile: File?= null

    var photosProvider:PhotosProvider? = null
    var sharedPref: SharedPref? = null
    var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_ecocomparte_update, container, false)

        sharedPref = SharedPref(requireActivity())

        imageViewEcoUpdate = myView?.findViewById(R.id.imageview_EcoUpdate)
        editTextEcoUpdate = myView?.findViewById(R.id.edittext_ecoupdate)
        buttonUpload = myView?.findViewById(R.id.btn_ecoupdate)

        imageViewEcoUpdate?.setOnClickListener { selectImage() }
        buttonUpload?.setOnClickListener { createEcophoto()  }

        getUserFromSession()
        photosProvider = PhotosProvider(user?.sessionToken!!)



        return myView
    }


    private fun getUserFromSession(){

        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }

    private fun createEcophoto () {
        val name = editTextEcoUpdate?.text.toString()

        if (imageFile != null){

            val photo = Photo(name = name)

            photosProvider?.create(imageFile!!,photo)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")// ver informacion que arroja el servidor

                    Toast.makeText(requireContext(), response.body()?.message,Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })

        }
        else {
            Toast.makeText(requireContext(),"Selecciona una imagen",Toast.LENGTH_LONG).show()
        }
    }

    private val startImageForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->

        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK ){
            val fileUri = data?.data
            imageFile = File(fileUri?.path) // el archivo que vamos a guardar comom imagen en el servidor
            imageViewEcoUpdate?.setImageURI(fileUri)
        }
        else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_LONG).show()

        }
        else {
            Toast.makeText(requireContext(),"Tarea se cancelo", Toast.LENGTH_LONG).show()
        }



    }

    private fun selectImage(){

        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080,1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }

    }

}