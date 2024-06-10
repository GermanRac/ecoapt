package com.optic.ecoapt.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.activities.client.home.ClientHomeActivity
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.UsersProvider
import com.optic.ecoapt.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SaveImageActivity : AppCompatActivity() {

    val TAG = "SaveImageActivity"

    var circleImageUser: CircleImageView? = null
    var buttonNext: Button? = null
    var buttonConfirm: Button? = null
    private  var imageFile: File?= null

    var usersProvider = UsersProvider()
    var user: User? = null
    var sharedPref: SharedPref?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)
        sharedPref = SharedPref(this)

        getUserFromSession()

        circleImageUser = findViewById(R.id.circleImageUser)
        buttonNext = findViewById(R.id.btn_next)
        buttonConfirm = findViewById(R.id.btn_confirm)

        circleImageUser?.setOnClickListener { selectImage()}

        buttonNext?.setOnClickListener { goToClientHome()}
        buttonConfirm?.setOnClickListener { saveImage()}


    }


    private fun saveImage(){

        if (imageFile != null && user !=null) {

            usersProvider.update(imageFile!!,user!!)?.enqueue(object :Callback<ResponseHttp>{
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Log.d(TAG,"RESPONSE: $response")
                    Log.d(TAG,"BODY: ${response.body()}")// ver informacion que arroja el servidor

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG,"Error: ${t.message}")
                    Toast.makeText(this@SaveImageActivity,"Error: ${t.message}",Toast.LENGTH_LONG).show()
                }

            })

        }
       else {
           Toast.makeText(this,"La imagen no puede ser nula, tampoco los datos de sesion del usuario",Toast.LENGTH_LONG).show()
       }

    }
    private fun goToClientHome() {
        val i = Intent(this, ClientHomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // eliminar historial de pantallas
        startActivity(i)
    }


    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
        }
    }



    private val startImageForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result:ActivityResult ->

        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK ){
            val fileUri = data?.data
            imageFile = File(fileUri?.path) // el archivo que vamos a guardar comom imagen en el servidor
            circleImageUser?.setImageURI(fileUri)
        }
        else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this,ImagePicker.getError(data),Toast.LENGTH_LONG).show()

        }
        else {
            Toast.makeText(this,"Tarea se cancelo",Toast.LENGTH_LONG).show()
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