package com.tawniya.simformtest.datasource.local

sealed class ResourceStatus<T> {
    object Loading: ResourceStatus<Nothing>()
    data class Success<T>(val data: T? = null): ResourceStatus<T>()
    data class Failure(val msg: String = "Something went wrong."): ResourceStatus<Nothing>()
}