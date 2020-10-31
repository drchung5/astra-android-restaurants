package com.dchung.astra.android.restaurantreviews.data.model

// CREATE TABLE restaurants (
//   name TEXT,
//   city TEXT,
//   state TEXT,
//   cuisine TEXT,
//   rating INT,
//   review TEXT,
//   PRIMARY KEY((state, city), name)
// );

data class RestaurantModelWrapper(
        var count: Int,
        var rows: List<RestaurantModel>
)

data class RestaurantModel (
    var name: String,
    var city: String,
    var state: String,
    var cuisine: String,
    var rating: Int,
    var review: String
)