package com.optic.ecoapt.fragments.client

import android.app.Activity
import android.os.Bundle
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
import com.optic.ecoapt.R
import java.io.File


class ClientEcocomparteUpdateFragment : Fragment() {

    var myView: View? = null
    var imageViewEcoUpdate: ImageView? = null
    var editTextEcoUpdate: EditText? = null
    var buttonUpload: Button? = null

    private  var imageFile: File?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_ecocomparte_update, container, false)

        imageViewEcoUpdate = myView?.findViewById(R.id.imageview_EcoUpdate)
        editTextEcoUpdate = myView?.findViewById(R.id.edittext_ecoupdate)
        buttonUpload = myView?.findViewById(R.id.btn_ecoupdate)

        imageViewEcoUpdate?.setOnClickListener { selectImage() }
        buttonUpload?.setOnClickListener { createEcophoto()  }



        return myView
    }

    private fun createEcophoto () {
        val txtphoto = editTextEcoUpdate?.text.toString()

        if (imageFile != null){

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