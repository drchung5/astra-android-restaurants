package com.dchung.astra.android.restaurantreviews.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://9f31a5a3-b963-46d3-a43f-4b261f2bfb0c-us-east1.apps.astra.datastax.com/api/rest/v1/"

class ApiClient {
    companion object{

        private var retrofit:Retrofit?=null

        fun getApiClient(): Retrofit {

            val gson = GsonBuilder()
                .setLenient()
                .create()
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofit!!
        }
    }
}