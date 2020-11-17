package com.dchung.astra.android.restaurantreviews.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// CREATE TABLE restaurants (
//   name TEXT,
//   city TEXT,
//   state TEXT,
//   cuisine TEXT,
//   rating INT,
//   review TEXT,
//   PRIMARY KEY((state, cit
//   y), name)
// );

@Parcelize
data class RestaurantVOWrapper(
        var count: Int,
        var rows: List<RestaurantVO>
) : Parcelable

@Parcelize
data class RestaurantVO (
    var name: String,
    var city: String,
    var state: String,
    var cuisine: String,
    var rating: Int,
    var review: String
) : Parcelable