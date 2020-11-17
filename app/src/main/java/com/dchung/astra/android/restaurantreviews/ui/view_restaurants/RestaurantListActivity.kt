package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.os.Bundle
import android.util.Log
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RestaurantDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RestaurantListActivity : AppCompatActivity() {

    private val TAG = "RestaurantListActivity"

    private lateinit var restaurantLiveData: MutableLiveData<RestaurantVOWrapper>

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var restaurantViewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.restaurant_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        restaurantViewModel = ViewModelProvider(this, RestaurantViewModelFactory())
            .get(RestaurantViewModel::class.java)

        restaurantLiveData = restaurantViewModel.getAllRestaurants()

        restaurantLiveData.observe(this, Observer<RestaurantVOWrapper> {

            Log.wtf(TAG,"restaurants.observe")
            Log.wtf(TAG,"""restaurantVOWrapper: ${it}""")
            Log.wtf(TAG,"""restaurants: ${it.rows}""")

            val recyclerView = findViewById<RecyclerView>(R.id.restaurant_list)
            recyclerView.adapter = RestaurantRecyclerViewAdapter(this, it, twoPane)

        })

    }
}