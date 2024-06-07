package com.optic.ecoapt.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.adapters.RewardAdapter
import com.optic.ecoapt.models.Reward
import com.optic.ecoapt.utils.SharedPref

class SelectRewardActivity:AppCompatActivity() {

    var recyclerViewReward: RecyclerView? = null
    var reward:Reward? = null
    var adapter:RewardAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_reward)

        recyclerViewReward = findViewById(R.id.recyclerview_rewards)
        recyclerViewReward?.layoutManager = LinearLayoutManager(this)

        getRewardFromSession()

        adapter = RewardAdapter(this, reward?.name!!)
        recyclerViewReward?.adapter = adapter


    }


    private fun getRewardFromSession() {

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if (!sharedPref.getData("reward").isNullOrBlank()) {
            //si el usuario existe en sesion
            val reward = gson.fromJson(sharedPref.getData("reward"), Reward::class.java)
        }

    }


}