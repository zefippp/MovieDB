package com.info.retrofitplaceholder.api

import com.info.retrofitplaceholder.pojo.Popular
import retrofit2.Call
import retrofit2.http.*


interface MovieApi {
    @GET("/3/movie/popular?api_key=9a0b8455b4ff730ac99eda03ccc126a0")
    fun getPopular(): Call<Popular?>?
}