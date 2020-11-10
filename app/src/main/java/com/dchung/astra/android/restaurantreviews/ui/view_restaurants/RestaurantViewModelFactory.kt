package com.dchung.astra.android.restaurantreviews.ui.view_restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dchung.astra.android.restaurantreviews.ui.login.LoginViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class RestaurantViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
        return RestaurantViewModel() as T
//        }
    }
}