package com.mewa.flickrapp.data.remote.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mewa.flickrapp.domain.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val context: Context
) : NetworkRepository {

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}