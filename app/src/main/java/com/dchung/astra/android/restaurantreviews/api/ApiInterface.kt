package com.dchung.astra.android.restaurantreviews.api

import com.dchung.astra.android.restaurantreviews.data.model.AuthTokenModel
import com.dchung.astra.android.restaurantreviews.data.model.CredentialsModel
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantModelWrapper
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth")
    fun createAuthToken(@Body credentialsModel: CredentialsModel) : Call<AuthTokenModel>

    @GET("keyspaces/reviews/tables/restaurants/rows")
    fun getAllRestaurants(@HeaderMap headers: Map<String, String>) : Call<RestaurantModelWrapper>

}