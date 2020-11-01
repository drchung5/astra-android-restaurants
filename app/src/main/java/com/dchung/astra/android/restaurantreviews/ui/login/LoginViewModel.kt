package com.dchung.astra.android.restaurantreviews.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.dchung.astra.android.restaurantreviews.Globals

import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.api.astra.ApiClient
import com.dchung.astra.android.restaurantreviews.api.astra.AuthTokenInterface
import com.dchung.astra.android.restaurantreviews.data.model.AuthTokenVO
import com.dchung.astra.android.restaurantreviews.data.model.CredentialsVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel() : ViewModel() {

    private val TAG = "LoginViewModel"

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val loggedIn = MutableLiveData<Boolean>()

    init {
        loggedIn.value = false
    }

    fun login(username: String, password: String){


        var loginInterface = ApiClient.getRetrofit().create(AuthTokenInterface::class.java)

        Log.wtf("ApiRepository:fetchAuthToken", "begin" )

        loginInterface?.createAuthToken(CredentialsVO(username,password))
                                        ?.enqueue(object : Callback<AuthTokenVO> {

            override fun onFailure(call: Call<AuthTokenVO>, t: Throwable) {
                Log.wtf("createAuthToken", t.message )
            }

            override fun onResponse(
                    call: Call<AuthTokenVO>,
                    response: Response<AuthTokenVO>
            ) {
                val res = response.body()
                Log.wtf("createAuthToken","""response code: ${response.code()}""" )

                // Stargate Auth method returns SC_201 on success
                if (response.code() == 201 &&  res!=null){

                    // save authToken for subsequent requests
                    Globals.authToken = res.authToken

                    Log.wtf(TAG, """AuthToken: ${res.authToken}""")

                    loggedIn.value = true
                }else{
                    Log.wtf("TAG","login failed" )
                }

            }
        })





//        liveAuthTokenModel?.observe(owner, Observer {
//
//            val i = if (it != null) {
//                Log.wtf("LoginActivity", """"AuthToken : ${it.authToken}""")
//
//                val headers = HashMap<String, String>()
//                headers["X-Cassandra-Request-Id"] = UUID.randomUUID().toString()
//                headers["X-Cassandra-Token"] = it.authToken
//
//                var liveRestaurantModelWrapper = apiRepository.getAllRestaurants(headers)
//                extractData(liveRestaurantModelWrapper)
//            } else {
//                Log.wtf("LoginActivity", "AuthToken : NULL")
//            }
//
//        })

        // can be launched in a separate asynchronous job
//        val result = loginRepository.login(username, password)
//
//        if (result is Result.Success) {
//            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }

    }

//    fun extractData(liveRestaurantModelWrapper: LiveData<RestaurantModelWrapper>) {
//
//        liveRestaurantModelWrapper?.observe(owner, Observer{
//            if(it!=null) {
//                for( r in it.rows ) {
//                    Log.wtf(r.name, """${r.city}, ${r.state}: ${r.rating} stars """)
//                }
//            } else {
//                Log.wtf("extractData", "RestaurantModelWrapper : NULL")
//            }
//        })
//
//    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // local username check
    private fun isUserNameValid(username: String): Boolean {
        Log.wtf(TAG, """isUserNameValid($username)""" )
        return username.isNotBlank()
    }

    // local password check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}