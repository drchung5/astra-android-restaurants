package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVO
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper

/**
 * A fragment representing a single Restaurant detail screen.
 * This fragment is either contained in a [RestaurantListActivity]
 * in two-pane mode (on tablets) or a [RestaurantDetailActivity]
 * on handsets.
 */
class RestaurantDetailFragment : Fragment() {

    private val TAG = "RestaurantDetailFragment"

    var restaurantId : Int = 0
    lateinit var restaurantVOWrapper : RestaurantVOWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.wtf(TAG,"""onCreate""")
        Log.wtf(TAG,"savedInstanceState: ${savedInstanceState}")
        Log.wtf(TAG, """Current restaurant: ${savedInstanceState?.getInt(ARG_RESTAURANT_ID).toString()}""" )
        Log.wtf(TAG, """Restaurant List: ${savedInstanceState?.getParcelable<RestaurantVOWrapper>(ARG_RESTAURANT_WRAPPER).toString()}""" )
//        Log.wtf(TAG, """Current restaurant: ${arguments?.getInt(ARG_RESTAURANT_ID).toString()}""" )
//        Log.wtf(TAG, """Restaurant List: ${arguments?.getParcelable<RestaurantVOWrapper>(ARG_RESTAURANT_WRAPPER).toString()}""" )

        arguments?.let {
            if (it.containsKey(ARG_RESTAURANT_ID) && it.containsKey(ARG_RESTAURANT_WRAPPER)) {

                Log.wtf(TAG,"Keys are present")

                restaurantId = it.getInt(ARG_RESTAURANT_ID)
                restaurantVOWrapper = it.getParcelable<RestaurantVOWrapper>(ARG_RESTAURANT_WRAPPER)!!

                Log.wtf(TAG, restaurantId.toString() )
                Log.wtf(TAG, restaurantVOWrapper.toString() )

                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    restaurantVOWrapper.rows.get(restaurantId).name

            }
        }

        Log.wtf(TAG,TAG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.restaurant_detail, container, false)

        Log.wtf(TAG,"onCreateView")
        Log.wtf(TAG, """restaurantVOWrapper ${restaurantVOWrapper}""")

        // Show the dummy content as text in a TextView.
        restaurantVOWrapper.rows.get(restaurantId).let {

            val holder = RestaurantDetailHolder(rootView)
            holder.name.text = it.name
            holder.cuisine.text = it.cuisine
            holder.city_state.text = """${it.city}, ${it.state}"""
            holder.review.text = it.review

        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_RESTAURANT_ID = "item_id"
        const val ARG_RESTAURANT_WRAPPER = "restaurant_list"

        // new instance allows setting bundle values in the fragment
        fun newInstance( restaurantId : Int, restaurantVOWrapper: RestaurantVOWrapper) : RestaurantDetailFragment {

            val theRestaurantDetailFragment = RestaurantDetailFragment()

            theRestaurantDetailFragment.let {

                val bundle = Bundle().apply {
                    putInt(ARG_RESTAURANT_ID, restaurantId)
                    putParcelable(ARG_RESTAURANT_WRAPPER, restaurantVOWrapper)
                }

                it.arguments = bundle
            }

            return theRestaurantDetailFragment
        }
    }
}

class RestaurantDetailHolder(view: View) {
    val name: TextView= view.findViewById(R.id.detail_name)
    val cuisine: TextView= view.findViewById(R.id.detail_cuisine)
    val city_state: TextView= view.findViewById(R.id.detail_address)
    val review: TextView= view.findViewById(R.id.detail_review)
}