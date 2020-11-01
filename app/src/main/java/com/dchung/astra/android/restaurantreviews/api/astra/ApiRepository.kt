package com.dchung.astra.android.restaurantreviews.api.astra

class ApiRepository {
//
//    lateinit var authToken: String
//
//    private var apiInterface: ApiInterface? = null
//
//    init {
//        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
//    }
//
//    fun createAuthToken(credentialsModel: CredentialsModel): LiveData<AuthTokenModel> {
//
//        Log.wtf("ApiRepository:fetchAuthToken", "begin" )
//
//        val data = MutableLiveData<AuthTokenModel>()
//
//        apiInterface?.createAuthToken(credentialsModel)?.enqueue(object : Callback<AuthTokenModel>{
//
//            override fun onFailure(call: Call<AuthTokenModel>, t: Throwable) {
//                Log.wtf("createAuthToken", t.message )
//                data.value = null
//            }
//
//            override fun onResponse(
//                call: Call<AuthTokenModel>,
//                response: Response<AuthTokenModel>
//            ) {
//                val res = response.body()
//                Log.wtf("createAuthToken","""response code: ${response.code()}""" )
//                // Stargate Auth method returns SC_201 on success
//                if (response.code() == 201 &&  res!=null){
//
//                    // save authToken for subsequent requests
//                    authToken = res.authToken
//
//                    data.value = res
//                    Log.wtf("createAuthToken","success : NOT NULL" )
//                }else{
//                    data.value = null
//                    Log.wtf("createAuthToken","success: NULL" )
//                }
//
//            }
//        })
//
//        return data
//    }
//
//    fun getAllRestaurants(@HeaderMap headers: Map<String, String>) : LiveData<RestaurantModelWrapper> {
//
//        Log.wtf("getAllRestaurants", "BEGIN")
//
//        val data = MutableLiveData<RestaurantModelWrapper>()
//
//        apiInterface?.getAllRestaurants(headers)?.enqueue(object : Callback<RestaurantModelWrapper>{
//
//
//            override fun onFailure(call: Call<RestaurantModelWrapper>, t: Throwable) {
//                Log.wtf("getAllRestaurants --- ERROR ", t.stackTraceToString() )
//                data.value = null
//            }
//
//            override fun onResponse(
//                    call: Call<RestaurantModelWrapper>,
//                    response: Response<RestaurantModelWrapper>
//            ) {
//                val res = response.body()
//                Log.wtf("getAllRestaurants","""response code: ${response.toString()}""" )
//
//                if (response.code() == 200 &&  res!=null){
//                    data.value = res
//                    Log.wtf("getAllRestaurants","success : Found Restaurants" )
//                }else{
//                    data.value = null
//                    Log.wtf("getAllRestaurants","failure" )
//                }
//
//            }
//        })
//
//        return data
//
//    }

}