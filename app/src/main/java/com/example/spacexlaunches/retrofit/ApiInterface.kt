package com.example.spacexlaunches.retrofit

import com.example.spacexlaunches.models.Launch.LaunchModel
import com.example.spacexlaunches.models.LaunchpadModel
import com.example.spacexlaunches.models.Rocket.RocketModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET()
    suspend fun getAllMissions (@Url url: String): Response<List<LaunchModel>>

    @GET()
    suspend fun getRocketDetail (@Url url: String): Response<RocketModel>

    @GET()
    suspend fun getLaunchpadDetail (@Url url: String): Response<LaunchpadModel>


    companion object {
        val BASE_URL = "https://api.spacexdata.com/v4/"

        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}