package com.optic.ecoapt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson

import com.optic.ecoapt.R
import com.optic.ecoapt.models.ResponseHttp
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.UsersProvider
import com.optic.ecoapt.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val TAG ="RegisterActivity"

    var imageViewToLogin: ImageView? = null
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var buttonRegister: Button? = null
    private var cbTermsConditions: CheckBox? = null


    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewToLogin = findViewById(R.id.image_go_login)
        editTextName= findViewById(R.id.edittext_name)
        editTextLastName= findViewById(R.id.edittext_lastname)
        editTextEmail= findViewById(R.id.edittext_email)
        editTextPassword= findViewById(R.id.edittext_password)
        editTextConfirmPassword= findViewById(R.id.edittext_confirm_password)
        cbTermsConditions = findViewById(R.id.cb_terms_conditions)
        buttonRegister= findViewById(R.id.btn_register)

        cbTermsConditions = findViewById(R.id.cb_terms_conditions)


        imageViewToLogin?.setOnClickListener { goToLogin() }
        buttonRegister?.setOnClickListener{
            if (cbTermsConditions?.isChecked == true) {
                register()
            } else {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register() {
        if (cbTermsConditions?.isChecked == true) {
            val name = editTextName?.text.toString()
            val lastname = editTextLastName?.text.toString()
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            val confirmPassword = editTextConfirmPassword?.text.toString()
            val consent = cbTermsConditions

            if (isValidForm(name = name,lastname = lastname,email = email,password = password, confirmpassword = confirmPassword)){
                //Toast.makeText(this, "El formulario es valido", Toast.LENGTH_SHORT).show()

                val user = User(
                    name = name,
                    lastname = lastname,
                    email = email,
                    password = password
//                    consent = consent
                )
                usersProvider.register(user)?.enqueue(object :Callback<ResponseHttp>{
                    override fun onResponse(
                        call: Call<ResponseHttp>,
                        response: Response<ResponseHttp>
                    ) {
                        if (response.body()?.isSuccess == true) {
                            saveUserInSession(response.body()?.data.toString())
                            goToClientHome()
                        }

                        Toast.makeText(this@RegisterActivity,response.body()?.message,Toast.LENGTH_LONG).show()
                        Log.d(TAG,"Response:${response}")
                        Log.d(TAG,"Body:${response.body()}")

                    }

                    override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {

                        Log.d(TAG,"Se produjo un error ${t.message}")
                        Toast.makeText(this@RegisterActivity,"Se produjo un error ${t.message}",Toast.LENGTH_LONG).show()

                    }

                })
            }

        }

    }

    private fun goToClientHome() {
        val i = Intent(this, SaveImageActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // eliminar historial de pantallas
        startActivity(i)
    }

    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user",user)
    }



    fun String.isEmailValid(): Boolean{

        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(
        name:String,
        lastname:String,
        email:String,
        password:String,
        confirmpassword:String

    ): Boolean{
        if (name.isBlank()){
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (lastname.isBlank()){
            Toast.makeText(this, "Debes ingresar el apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()){
            Toast.makeText(this, "Debes ingresar un email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isBlank()){
            Toast.makeText(this, "Debes ingresar un password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (confirmpassword.isBlank()){
            Toast.makeText(this, "Ingresa la confirmacion de contrasena", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.isEmailValid()){
            Toast.makeText(this, "El email no es valido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmpassword){
            Toast.makeText(this, "Las contrasenas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
