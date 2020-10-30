package com.dchung.astra.android.restaurantreviews.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dchung.astra.android.restaurantreviews.data.model.AuthTokenModel
import com.dchung.astra.android.restaurantreviews.data.model.CredentialsModel
import retrofit2.http.Body
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAuthToken(credentialsModel: CredentialsModel): LiveData<AuthTokenModel> {

        Log.wtf("ApiRepository:fetchAuthToken", "begin" )

        val data = MutableLiveData<AuthTokenModel>()

        apiInterface?.fetchAuthToken(credentialsModel)?.enqueue(object : Callback<AuthTokenModel>{

            override fun onFailure(call: Call<AuthTokenModel>, t: Throwable) {
                Log.wtf("ApiRepository", t.message )
                data.value = null
            }

            override fun onResponse(
                call: Call<AuthTokenModel>,
                response: Response<AuthTokenModel>
            ) {
                val res = response.body()
                Log.wtf("ApiRepository","""response code: ${response.code()}""" )
                if (response.code() == 201 &&  res!=null){
                    data.value = res
                    Log.wtf("ApiRepository","success : NOT NULL" )
                }else{
                    data.value = null
                    Log.wtf("ApiRepository","success: NULL" )
                }

            }
        })

        return data
    }

}