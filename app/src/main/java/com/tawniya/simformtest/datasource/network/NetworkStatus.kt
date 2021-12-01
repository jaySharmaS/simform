package com.tawniya.simformtest.datasource.network

sealed class NetworkStatus<out T> {
    object Loading: NetworkStatus<Nothing>()
    data class Success<T>(val data: T): NetworkStatus<T>()
    data class Failure(val msg: String = "Something went wrong."): NetworkStatus<Nothing>()
}