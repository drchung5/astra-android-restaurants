package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dchung.astra.android.restaurantreviews.Globals
import com.dchung.astra.android.restaurantreviews.api.astra.ApiClient
import com.dchung.astra.android.restaurantreviews.api.astra.RestaurantInterface
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class RestaurantViewModel() : ViewModel() {

    private val TAG = "RestaurantViewModel"

    fun getAllRestaurants() : MutableLiveData<RestaurantVOWrapper> {

        val restaurantLiveData = MutableLiveData<RestaurantVOWrapper>()

        val restaurantInterface = ApiClient.getRetrofit().create(RestaurantInterface::class.java)

        Log.wtf(TAG, "getAllRestaurants")

        val headers = HashMap<String, String>()
        headers[Globals.ID_HEADER] = UUID.randomUUID().toString()
        headers[Globals.TOKEN_HEADER] = Globals.authToken

        restaurantInterface.getAllRestaurants(headers)
            .enqueue(object : Callback<RestaurantVOWrapper> {

                override fun onFailure(call: Call<RestaurantVOWrapper>, t: Throwable) {
                    Log.wtf(TAG, t.message )
                }

                override fun onResponse(
                    call: Call<RestaurantVOWrapper>,
                    response: Response<RestaurantVOWrapper>
                ) {
                   if( response.isSuccessful ) {
                       restaurantLiveData.value = response.body()
                   }
                }
            })

        return restaurantLiveData
    }

}
