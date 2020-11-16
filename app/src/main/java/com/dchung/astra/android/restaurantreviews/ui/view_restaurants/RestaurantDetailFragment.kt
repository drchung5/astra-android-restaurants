package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dchung.astra.android.restaurantreviews.R

/**
 * A fragment representing a single Restaurant detail screen.
 * This fragment is either contained in a [RestaurantListActivity]
 * in two-pane mode (on tablets) or a [RestaurantDetailActivity]
 * on handsets.
 */
class RestaurantDetailFragment : Fragment() {

    private val TAG = "RestaurantDetailFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_RESTAURANT_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
//                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
//                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.content

                Log.wtf(TAG, "${it.getInt(ARG_RESTAURANT_ID)}")

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.restaurant_detail, container, false)

//        // Show the dummy content as text in a TextView.
//        item?.let {
//            rootView.findViewById<TextView>(R.id.restaurant_detail).text = it.details
//        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_RESTAURANT_ID = "item_id"
    }
}