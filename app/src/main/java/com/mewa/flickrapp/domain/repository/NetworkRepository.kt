package com.mewa.flickrapp.domain.repository

interface NetworkRepository {

    fun isNetworkAvailable(): Boolean
}