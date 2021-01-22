package com.info.retrofitplaceholder.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private var mInstance: NetworkService? = null
    private val BASE_URL = "https://api.themoviedb.org"
    private var mRetrofit: Retrofit? = null

    private fun NetworkService(): NetworkService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return this
    }

    fun getInstance(): NetworkService? {
        if (mInstance == null) {
            mInstance = NetworkService()
        }
        return mInstance
    }

    fun getJSONApi(): MovieApi? {
        return mRetrofit!!.create(MovieApi::class.java)
    }
}