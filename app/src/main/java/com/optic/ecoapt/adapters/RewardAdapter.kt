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

class RewardAdapter(val context:Activity, val reward: String) :RecyclerView.Adapter<RewardAdapter.RewardsViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): RewardsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_reward,parent,false)
        return RewardsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reward.size
    }

    override fun onBindViewHolder(holder: RewardsViewHolder,position: Int){
         val reward = reward[position]//cada reward

        holder.textViewReward.text = reward.name
        Glide.with(context).load(reward.image).into(holder.imageViewReward)
    }



    class RewardsViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textViewReward:TextView
        val textViewDescription:TextView
        val textViewRewardPoints:TextView
        val imageViewReward:ImageView


        init {
            textViewReward = view.findViewById(R.id.textview_reward_name)
            textViewDescription = view.findViewById(R.id.textview_reward_description)
            textViewRewardPoints = view.findViewById(R.id.textview_reward_points)
            imageViewReward = view.findViewById(R.id.imageview_reward)
        }
    }
}