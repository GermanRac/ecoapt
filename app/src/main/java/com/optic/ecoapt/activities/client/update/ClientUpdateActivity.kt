package com.optic.ecoapt.activities.client.update

import android.app.Activity
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
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.UsersProvider
import com.optic.ecoapt.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ClientUpdateActivity : AppCompatActivity() {

    val TAG = "ClientUpdateActivity"

    var circleImageUser: CircleImageView?= null
    var editTextName: EditText?= null
    var editTextLastname: EditText?= null
    var buttonUpdate: Button?= null
    var sharedPref: SharedPref? = null
    var user: User? = null

    private  var imageFile: File?= null
    var usersProvider = UsersProvider()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_update)

        sharedPref = SharedPref(this)
        circleImageUser = findViewById(R.id.circleimage_user)
        editTextName = findViewById(R.id.edittext_name)
        editTextLastname = findViewById(R.id.edittext_lastname)
        buttonUpdate = findViewById(R.id.btn_update)

        getUserFromSession()

        editTextName?.setText(user?.name)
        editTextLastname?.setText(user?.lastname)

        if (!user?.image.isNullOrBlank()){
            Glide.with(this).load(user?.image).into(circleImageUser!!)
        }
        circleImageUser?.setOnClickListener{selectImage()}
        buttonUpdate?.setOnClickListener{updateData()}

    }



    private fun updateData(){

        val name = editTextName?.text.toString()
        val lastname = editTextLastname?.text.toString()

        user?.name = name
        user?.lastname = lastname

        if (imageFile != null ){

            usersProvider.update(imageFile!!,user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")// ver informacion que arroja el servidor
                    saveUserInSession(response.body()?.data.toString())
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@ClientUpdateActivity, "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })

        }
        else{


            usersProvider.updateWithoutImage(user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")// ver informacion que arroja el servidor
                    saveUserInSession(response.body()?.data.toString())
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@ClientUpdateActivity, "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })


        }


    }

    private fun saveUserInSession(data: String) {
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref?.save("user", user)
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