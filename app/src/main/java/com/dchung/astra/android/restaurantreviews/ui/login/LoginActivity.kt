package com.dchung.astra.android.restaurantreviews.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.dchung.astra.android.restaurantreviews.R
import com.dchung.astra.android.restaurantreviews.ui.view_restaurants.RestaurantListActivity

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loggedIn.observe(this@LoginActivity, Observer {

            val loggedIn = it ?: return@Observer

            if (loggedIn) {
                Log.wtf(TAG, "LoginActivity informed of successful login")

                // go to RestaurantsListActivity
                startActivity(Intent(this, RestaurantListActivity::class.java))
            }
            setResult(Activity.RESULT_OK)

        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(username.text.toString(), password.text.toString())
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

//    fun login(username: String, password: String) {
//        loginViewModel.login(username, password)?.observe(this, Observer {
//
//            if (it){
//                Log.wtf(TAG, "Logged in")
//            }else{
//                Log.wtf(TAG, "Login FAILED")
//            }
//
//        })
//    }

//    fun doAuth() {
//
//        var apiRepository = ApiRepository()
//
//        var liveAuthTokenModel = apiRepository.createAuthToken(
//            CredentialsModel("dbuser","password"))
//
//        liveAuthTokenModel?.observe(this, Observer {
//
//            if (it!=null){
//                Log.wtf("LoginActivity", """"AuthToken : ${it.authToken}""")
//
//                val headers = HashMap<String, String>()
//                headers["X-Cassandra-Request-Id"] = UUID.randomUUID().toString()
//                headers["X-Cassandra-Token"] = it.authToken
//
//                var liveRestaurantModelWrapper = apiRepository.getAllRestaurants(headers)
//                extractData(liveRestaurantModelWrapper)
//            }else{
//                Log.wtf("LoginActivity", "AuthToken : NULL")
//            }
//
//        })
//
//    }

//    fun extractData(liveRestaurantModelWrapper: LiveData<RestaurantModelWrapper>) {
//
//        liveRestaurantModelWrapper?.observe(this, Observer{
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

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

