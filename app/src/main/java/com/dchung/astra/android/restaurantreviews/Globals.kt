package com.dchung.astra.android.restaurantreviews

import android.app.Application

class Globals {
    companion object {
        lateinit var authToken: String
        const val ID_HEADER = "X-Cassandra-Request-Id"
        const val TOKEN_HEADER = "X-Cassandra-Token"
    }
}