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
import com.optic.ecoapt.models.Event
import com.optic.ecoapt.utils.SharedPref

class EventsAdapter (val context: Activity, val events: ArrayList<Event>): RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    val sharedPref = SharedPref(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_events, parent, false)
        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {

        val event = events[position] // CADA UNO DE LOS EVENTOS

        holder.textViewEventName.text = event.name
        holder.textViewEventDescription.text = event.description
        holder.textViewEventDate.text = event.date
        Glide.with(context).load(event.image).into(holder.imageViewEvent)


//        holder.itemView.setOnClickListener { goToRol(rol) }
    }

//    private fun goToRol(rol: Rol) {
//        val i = Intent(context, RestaurantHomeActivity::class.java)
//        context.startActivity(i)
//    }

    class EventsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewEventName: TextView
        val textViewEventDescription: TextView
        val textViewEventDate: TextView
        val imageViewEvent: ImageView

        init {
            textViewEventName = view.findViewById(R.id.textview_eventName)
            textViewEventDescription = view.findViewById(R.id.textview_eventDescription)
            textViewEventDate = view.findViewById(R.id.textview_eventDate)
            imageViewEvent = view.findViewById(R.id.imageview_event)
        }

    }

}