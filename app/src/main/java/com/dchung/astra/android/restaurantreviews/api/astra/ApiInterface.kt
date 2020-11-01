package com.dchung.astra.android.restaurantreviews.api.astra

import com.dchung.astra.android.restaurantreviews.data.model.AuthTokenVO
import com.dchung.astra.android.restaurantreviews.data.model.CredentialsVO
import com.dchung.astra.android.restaurantreviews.data.model.RestaurantVOWrapper
import retrofit2.Call
import retrofit2.http.*

interface AuthTokenInterface {

    @POST("auth")
    fun createAuthToken(@Body credentialsVO: CredentialsVO) : Call<AuthTokenVO>

}

interface RestaurantInterface {

    @GET("keyspaces/reviews/tables/restaurants/rows")
    fun getAllRestaurants(@HeaderMap headers: Map<String, String>) : Call<RestaurantVOWrapper>

}