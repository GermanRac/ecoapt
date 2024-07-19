package com.optic.ecoapt.activities.client.rewards.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.models.Reward

class ClientRewardsDetailActivity : AppCompatActivity() {

    var reward: Reward? = null
    val gson = Gson ()

    var imageView:ImageView? = null
    var textViewName:TextView? = null
    var textViewDescription: TextView? = null
    var textViewQuantity: TextView? = null
    var buttonClaim:Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_rewards_detail)

        reward = gson.fromJson(intent.getStringExtra("reward"),Reward::class.java)

        imageView = findViewById(R.id.imageview_reward_detail)
        textViewName = findViewById(R.id.textview_name)
        textViewDescription = findViewById(R.id.textview_description)
        textViewQuantity = findViewById(R.id.textview_quantity)
        buttonClaim = findViewById(R.id.btn_claim_reward)


        // Cargar la imagen usando Glide
        reward?.image_url?.let { imageUrl ->
            Glide.with(this)
                .load(imageUrl)
                .into(imageView!!)
        }

        textViewName?.text = reward?.name
        textViewDescription?.text = reward?.description
        textViewQuantity?.text = "${reward?.quantity}"



    }
}