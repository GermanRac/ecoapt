package com.optic.ecoapt.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.optic.ecoapt.R
import com.optic.ecoapt.models.Reward
import com.optic.ecoapt.utils.SharedPref

class RewardsAdapter (val context: Activity, val rewards: ArrayList<Reward>): RecyclerView.Adapter<RewardsAdapter.RewardsViewHolder>() {

    val sharedPref = SharedPref(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_reward, parent, false)
        return RewardsViewHolder(view)
    }


    override fun getItemCount(): Int {
        return rewards.size
    }


    override fun onBindViewHolder(holder: RewardsViewHolder, position: Int) {

        val reward = rewards[position] // CADA UNO DE LOS REWARDS

        holder.textViewRewardName.text = reward.name
        holder.textViewRewardDescription.text = reward.description
        holder.textViewRewardPoints.text = reward.points
        Glide.with(context).load(reward.image).into(holder.imageViewReward)


//        holder.itemView.setOnClickListener { goToRol(rol) }
    }

//    private fun goToRol(rol: Rol) {
//        val i = Intent(context, RestaurantHomeActivity::class.java)
//        context.startActivity(i)
//    }

    class RewardsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewRewardName: TextView
        val textViewRewardDescription: TextView
        val textViewRewardPoints: TextView
        val imageViewReward: ImageView

        init {
            textViewRewardName = view.findViewById(R.id.textview_reward_name)
            textViewRewardDescription = view.findViewById(R.id.textview_reward_description)
            textViewRewardPoints = view.findViewById(R.id.textview_reward_points)
            imageViewReward = view.findViewById(R.id.imageview_reward)
        }

    }

}