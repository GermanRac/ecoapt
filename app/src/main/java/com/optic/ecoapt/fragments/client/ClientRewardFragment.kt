package com.optic.ecoapt.fragments.client

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

class ClientRewardFragment : Fragment() {

    private val TAG = "ClientRewardFragment"
    var myView: View? = null
    var recyclerViewRewards:RecyclerView? = null
    var rewardsProvider : RewardsProvider? = null
    var adapter: RewardsAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
//    var rewards:ArrayList<Reward> = ArrayList()
    var rewards = ArrayList<Reward>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_reward, container, false)

        recyclerViewRewards = myView?.findViewById(R.id.recyclerview_rewards)
//        recyclerViewRewards?.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerViewRewards?.layoutManager = LinearLayoutManager(requireContext())
        sharedPref = SharedPref(requireActivity())
        getUserFromSession()

//        rewardsProvider = RewardsProvider(user?.sessionToken!!)
        user?.sessionToken?.let { token ->
            rewardsProvider = RewardsProvider(token)
        }

        getRewards()


        return myView
    }





    private fun getRewards() {
        rewardsProvider?.getAll()?.enqueue(object : Callback<ArrayList<Reward>> {
            override fun onResponse(call: Call<ArrayList<Reward>>, response: Response<ArrayList<Reward>>) {
                if (response.body() != null) {
                    rewards.addAll(response.body()!!)
                    adapter = RewardsAdapter(requireActivity(), rewards)
                    recyclerViewRewards?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Reward>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getUserFromSession(){

        val gson = Gson()
        val userData = sharedPref?.getData("user")

        if (!userData.isNullOrBlank()) {
            user = gson.fromJson(userData, User::class.java)
            Log.d(TAG, "Usuario: $user")
        }

//        if (!sharedPref?.getData("user").isNullOrBlank()) {
//            //si el usuario existe en sesion
//            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java )
//            Log.d(TAG,"Usuario: $user")
//        }
    }


}