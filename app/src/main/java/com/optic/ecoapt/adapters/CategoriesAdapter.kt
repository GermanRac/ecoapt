package com.optic.ecoapt.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.optic.ecoapt.R
import com.optic.ecoapt.activities.client.rewards.list.ClientRewardsListActivity
import com.optic.ecoapt.fragments.client.ClientRewardFragment
import com.optic.ecoapt.models.Category
import com.optic.ecoapt.models.Reward

import com.optic.ecoapt.utils.SharedPref

class CategoriesAdapter (val context: Activity, val categories : ArrayList<Category>): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    val sharedPref = SharedPref(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        Log.d("CategoriesAdapter", "onCreateViewHolder called")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_categories, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("CategoriesAdapter", "getItemCount: ${categories.size}")
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        Log.d("CategoriesAdapter", "onBindViewHolder called for position: $position")
        val category = categories[position] // CADA UNO DE LOS Categories

        holder.textviewCategory.text = category.name
        Glide.with(context).load(category.image).into(holder.imageViewCategory)

        holder.itemView.setOnClickListener{ gotoRewards(category)}

    }

    private fun gotoRewards(category: Category){
        val i = Intent (context,ClientRewardsListActivity::class.java)
        i.putExtra("idCategory",category.id)
        context.startActivity(i)
    }



    class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textviewCategory: TextView
        val imageViewCategory: ImageView


        init {
            textviewCategory = view.findViewById(R.id.textview_category)
            imageViewCategory = view.findViewById(R.id.imageView_category)
        }

    }

}