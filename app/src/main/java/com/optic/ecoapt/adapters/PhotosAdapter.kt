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
import com.optic.ecoapt.models.Photo
import com.optic.ecoapt.utils.SharedPref

class PhotosAdapter (val context: Activity, val photos: ArrayList<Photo>): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_photos, parent, false)
        return PhotosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {

        val photo = photos[position] // CADA UNO DE LAS FOTOS

        holder.textViewPhotoName.text = photo.name
        Glide.with(context).load(photo.image).into(holder.imageViewPhoto)


//        holder.itemView.setOnClickListener { goToRol(rol) }
    }

//    private fun goToRol(rol: Rol) {
//        val i = Intent(context, RestaurantHomeActivity::class.java)
//        context.startActivity(i)
//    }

    class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewPhotoName: TextView
        val imageViewPhoto: ImageView

        init {
            textViewPhotoName = view.findViewById(R.id.textview_photoName)
            imageViewPhoto = view.findViewById(R.id.imageview_photo)
        }

    }

}