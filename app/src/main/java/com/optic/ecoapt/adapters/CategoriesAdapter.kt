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
import com.optic.ecoapt.models.Category

import com.optic.ecoapt.utils.SharedPref

class CategoriesAdapter (val context: Activity, val categories : ArrayList<Category>): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    val sharedPref = SharedPref(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_categories, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        val category = categories[position] // CADA UNO DE LOS Categories

        holder.textviewCategory.text = category.name
        Glide.with(context).load(category.image).into(holder.imageViewCategory)

    }



    class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textviewCategory: TextView
        val imageViewCategory: ImageView


        init {
            textviewCategory = view.findViewById(R.id.textview_category)
            imageViewCategory = view.findViewById(R.id.imageview_category)
        }

    }

}