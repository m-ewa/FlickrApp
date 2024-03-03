package com.mewa.domain.repository

interface NetworkRepository {

    fun isNetworkAvailable(): Boolean
}