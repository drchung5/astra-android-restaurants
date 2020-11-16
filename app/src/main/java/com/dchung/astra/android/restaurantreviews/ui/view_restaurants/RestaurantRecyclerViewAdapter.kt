package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper
import android.graphics.drawable.Drawable as Drawable

class RestaurantRecyclerViewAdapter(
                            private val context: RestaurantListActivity,
                            private val restaurantVOWrapper: RestaurantVOWrapper,
                            private val twoPane: Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "RestaurantRecyclerViewAdapter"

    private val onClickListener: View.OnClickListener

        init {

            onClickListener = View.OnClickListener {

                if( it is LinearLayout) {

                    val view: LinearLayout = it as LinearLayout
                    val itemId = view.tag as Int
                    Log.wtf(TAG, """OnClickListener: ${itemId}""")

                    if (twoPane) {
                        val fragment = RestaurantDetailFragment().apply {
                            arguments = Bundle().apply {
                                putInt(RestaurantDetailFragment.ARG_RESTAURANT_ID, itemId)
                            }
                        }
                        context.supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.restaurant_detail_container, fragment)
                                .commit()
                    } else {
                        val intent = Intent(it.context, RestaurantDetailActivity::class.java).apply {
                            putExtra(RestaurantDetailFragment.ARG_RESTAURANT_ID, itemId  )
                        }
                        it.context.startActivity(intent)
                    }
                }
                }

            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_content, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurantVO = restaurantVOWrapper.rows.get(position)
        val restaurantViewHolder = holder as RestaurantViewHolder

        with(restaurantViewHolder) {

            with( listContentLayout ) {
                listContentLayout.setOnClickListener(onClickListener)
                tag = position
            }

            with(idView) {
                text = restaurantVO.name
            }

            with( ratingBar ) {
                numStars = restaurantVO.rating
                rating = restaurantVO.rating.toFloat()
            }

            with( contentView ) {
                if( twoPane ) {
                    contentView.visibility = View.GONE
                    val params = LinearLayout.LayoutParams( 230, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
                    holder.idView.layoutParams = params
                } else {
                    text = """${restaurantVO.city}, ${restaurantVO.state}"""
                    tag = position
                }
            }
        }

        val ratingBarDrawable = holder.ratingBar.getProgressDrawable()
        DrawableCompat.setTint(ratingBarDrawable, Color.argb(255,255,0,0) )

        Log.wtf(TAG,"""${restaurantVO.name}  ${restaurantVO.city}, ${restaurantVO.state}  ${restaurantVO.rating}""")
    }

    override fun getItemCount(): Int {
        return restaurantVOWrapper.count
    }
}

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val listContentLayout : LinearLayout = view.findViewById(R.id.list_contents_layout)
    val idView: TextView = view.findViewById(R.id.restaurant_name)
    val contentView: TextView = view.findViewById(R.id.city_state)
    val ratingBar: RatingBar = view.findViewById(R.id.rating)
}