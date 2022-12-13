package com.example.spacexlaunches.retrofit

import com.example.spacexlaunches.model.models.LaunchModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET()
    fun getData(@Url url: String): Call<List<LaunchModel>>

    //@GET()
    //suspend fun getData(@Url url: String): Response<List<Launch>>

    companion object {
        val BASE_URL = "https://api.spacexdata.com/v5/"

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