package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVO
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper

class RestaurantRecyclerViewAdapter(
                            private val context: Activity,
                            private val restaurantVOWrapper: RestaurantVOWrapper,
                            private val twoPane: Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private val onClickListener: View.OnClickListener
//
//        init {
//            onClickListener = View.OnClickListener { v ->
//                val item = v.tag as DummyContent.DummyItem
//                if (twoPane) {
//                    val fragment = RestaurantDetailFragment().apply {
//                        arguments = Bundle().apply {
//                            putString(RestaurantDetailFragment.ARG_ITEM_ID, item.id)
//                        }
//                    }
//                    context.supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.restaurant_detail_container, fragment)
//                            .commit()
//                } else {
//                    val intent = Intent(v.context, RestaurantDetailActivity::class.java).apply {
//                        putExtra(RestaurantDetailFragment.ARG_ITEM_ID, item.id)
//                    }
//                    v.context.startActivity(intent)
//                }
//            }
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_content, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurantVO = restaurantVOWrapper.rows.get(position)
        val restaurantViewHolder = holder as RestaurantViewHolder
        holder.idView.text = restaurantVO.name
        holder.contentView.text = """${restaurantVO.city}"""
    }

    override fun getItemCount(): Int {
        return restaurantVOWrapper.count
    }
}

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val idView: TextView = view.findViewById(R.id.id_text)
    val contentView: TextView = view.findViewById(R.id.content)
}