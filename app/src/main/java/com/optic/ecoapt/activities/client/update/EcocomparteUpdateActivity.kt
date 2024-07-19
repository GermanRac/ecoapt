package com.optic.ecoapt.activities.client.update

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.UsersProvider
import com.optic.ecoapt.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File


class EcocomparteUpdateActivity : AppCompatActivity() {

    private val TAG = "EcocomparteUpdateActivity"
    var circleImagePhoto: CircleImageView?= null
    var editTextNamePhoto: EditText?= null
    var buttonUpdate: Button?= null
    var sharedPref: SharedPref? = null
    var user: User? = null

    private  var imageFile: File?= null
    //    var usersProvider: UsersProvider? = null
    var usersProvider =  UsersProvider()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecocomparte_update)

        sharedPref = SharedPref(this)
        circleImagePhoto = findViewById(R.id.circleimage_photo)
        editTextNamePhoto = findViewById(R.id.edittext_photoname)
        buttonUpdate = findViewById(R.id.btn_update)

        getUserFromSession()

        if (!user?.image.isNullOrBlank()){
            Glide.with(this).load(user?.image).into(circleImagePhoto!!)
        }
        circleImagePhoto?.setOnClickListener{selectImage()}
        buttonUpdate?.setOnClickListener{}






    }



    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
            Log.d(TAG,"Usuario: $user")
        }
    }


    private fun goToUpdate(){
        val i = Intent(this, ClientUpdateActivity::class.java)
        startActivity(i)
    }

    private val startImageForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->

        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK ){
            val fileUri = data?.data
            imageFile = File(fileUri?.path) // el archivo que vamos a guardar comom imagen en el servidor
            circleImagePhoto?.setImageURI(fileUri)
        }
        else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this,ImagePicker.getError(data), Toast.LENGTH_LONG).show()

        }
        else {
            Toast.makeText(this,"Tarea se cancelo", Toast.LENGTH_LONG).show()
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