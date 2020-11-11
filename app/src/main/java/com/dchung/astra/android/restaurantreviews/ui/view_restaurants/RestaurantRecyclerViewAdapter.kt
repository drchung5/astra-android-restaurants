package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVO

class RestaurantRecyclerViewAdapter(context: Activity, restaurants: Array<RestaurantVO>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_content, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val idView: TextView = view.findViewById(R.id.id_text)
    val contentView: TextView = view.findViewById(R.id.content)
}