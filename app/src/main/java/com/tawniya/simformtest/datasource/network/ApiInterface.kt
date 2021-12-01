package com.tawniya.simformtest.datasource.network

import com.tawniya.simformtest.datasource.network.dto.UserDtoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    suspend fun getUsers(@Query("results") numberOfItems: Int = 100): Response<UserDtoResponse>

}