package com.optic.ecoapt.activities.client.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.optic.ecoapt.R
import com.optic.ecoapt.adapters.PhotosAdapter
import com.optic.ecoapt.adapters.RewardsAdapter
import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.models.Reward
import com.optic.ecoapt.models.User
import com.optic.ecoapt.providers.RewardsProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientRewardsListActivity : AppCompatActivity() {
    private val TAG = "ClientRewardsList"

    var recyclerViewRewards:RecyclerView? = null
    var adapter: RewardsAdapter? = null
    var user: User? = null
    var rewardsProvider : RewardsProvider? = null
    var rewards:ArrayList<Reward> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_rewards_list)

        recyclerViewRewards = findViewById(R.id.recyclerview_rewards)
        recyclerViewRewards?.layoutManager = GridLayoutManager (this,2)


    }


    private fun getRewards() {
        rewardsProvider?.getAll()?.enqueue(object : Callback<ArrayList<Reward>> {
            override fun onResponse(call: Call<ArrayList<Reward>>, response: Response<ArrayList<Reward>>) {
                if (response.body() != null) {
                    rewards.addAll(response.body()!!)
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

}