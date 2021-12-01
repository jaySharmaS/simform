package com.tawniya.simformtest.datasource.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val nc = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            nc?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false || nc?.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI) ?: false
        } else false
    }


}