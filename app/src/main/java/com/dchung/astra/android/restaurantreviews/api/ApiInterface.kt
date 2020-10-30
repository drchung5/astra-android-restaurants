package com.dchung.astra.android.restaurantreviews.api

import com.dchung.astra.android.restaurantreviews.data.model.AuthTokenModel
import com.dchung.astra.android.restaurantreviews.data.model.CredentialsModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth")
    fun fetchAuthToken(@Body credentialsModel: CredentialsModel) : AuthTokenModel

}