package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.dchung.astra.android.restaurantreviews.R

/**
 * An activity representing a single Restaurant detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [RestaurantListActivity].
 */
class RestaurantDetailActivity : AppCompatActivity() {

    private val TAG = "RestaurantDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = RestaurantDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(RestaurantDetailFragment.ARG_RESTAURANT_ID,
                            intent.getIntExtra(RestaurantDetailFragment.ARG_RESTAURANT_ID,0))
                    putParcelable(RestaurantDetailFragment.ARG_RESTAURANT_WRAPPER,
                            intent.getParcelableExtra(RestaurantDetailFragment.ARG_RESTAURANT_WRAPPER))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.restaurant_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, RestaurantListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}