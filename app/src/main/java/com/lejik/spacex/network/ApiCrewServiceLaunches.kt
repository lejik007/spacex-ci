package com.lejik.spacex.network

import com.lejik.spacex.model.Crew
import com.lejik.spacex.model.Docs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiCrewServiceLaunches {
    @GET("crew")
//    suspend fun getPhotos(): MutableLiveData<List<Docs>>
    suspend fun getCrews(): List<Crew>
//    suspend fun getPhotos(): String
//    fun getPhotos(): Call<String>

    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/v4/"

        fun create() : ApiCrewServiceLaunches {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiCrewServiceLaunches::class.java)
            return retrofit
        }
    }
}

/*
*
interface ApiServiceLaunches {
    @GET("launches")
    suspend fun getPhotos(): List<Docs>
//    suspend fun getPhotos(): String
//    fun getPhotos(): Call<String>
}
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

object ApiObject {
    val retrofitService: ApiServiceLaunches by lazy {
        retrofit.create(ApiServiceLaunches::class.java)
    }
}*/