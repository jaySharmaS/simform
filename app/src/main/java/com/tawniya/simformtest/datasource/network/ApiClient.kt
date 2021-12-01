package com.tawniya.simformtest.datasource.network

import com.google.gson.GsonBuilder
import com.tawniya.simformtest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): ApiInterface {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(buildGSONConverterFactory())
                .client(getHttpClient())
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }

    private fun buildGSONConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        return GsonConverterFactory.create(builder.create())
    }

    private fun getHttpClient(): OkHttpClient {
        val client: OkHttpClient
        if (BuildConfig.DEBUG) {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            client = OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        } else {

            client = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        }
        return client
    }

}