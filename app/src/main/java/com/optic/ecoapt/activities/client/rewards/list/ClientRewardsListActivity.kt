package com.optic.ecoapt.activities.client.rewards.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.optic.ecoapt.R
import com.optic.ecoapt.adapters.RewardsAdapter
import com.optic.ecoapt.models.Reward
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.RewardsProvider
import com.optic.ecoapt.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientRewardsListActivity : AppCompatActivity() {


    private val TAG = "ClientRewardFragment"

    var recyclerViewRewards:RecyclerView? = null
    var rewardsProvider : RewardsProvider? = null
    var adapter: RewardsAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var rewards:ArrayList<Reward> = ArrayList()
//    var rewards = ArrayList<Reward>()
    var idCategory:String? = null



    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_rewards_list)

        sharedPref = SharedPref(this)

        idCategory = intent.getStringExtra("idCategory")

        getUserFromSession()
        rewardsProvider = RewardsProvider(user?.sessionToken!!)

        recyclerViewRewards = findViewById(R.id.recyclerview_rewards)
        recyclerViewRewards?.layoutManager = GridLayoutManager(this, 2)

        getRewards()
    }

    private fun getRewards() {
        rewardsProvider?.findByCategory(idCategory!!)?.enqueue(object :
            Callback<ArrayList<Reward>> {
            override fun onResponse(call: Call<ArrayList<Reward>>, response: Response<ArrayList<Reward>>) {
                if (response.body() != null) {
                    rewards = response.body()!!
                    adapter = RewardsAdapter(this@ClientRewardsListActivity, rewards)
                    recyclerViewRewards?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Reward>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(this@ClientRewardsListActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getUserFromSession() {

        val gson = Gson()

        if (!sharedPref?.getData("user").isNullOrBlank()) {
            //si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }

//        if (!userData.isNullOrBlank()) {
//            user = gson.fromJson(userData, User::class.java)
//            Log.d(TAG, "Usuario: $user")
//        }


    }


}





