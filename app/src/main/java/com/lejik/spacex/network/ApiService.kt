package com.lejik.spacex.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lejik.spacex.model.Docs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.spacexdata.com/v4/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("launches")
    suspend fun getPhotos(): List<Docs>
//    suspend fun getPhotos(): String
//    fun getPhotos(): Call<String>
}

object ApiObject {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}