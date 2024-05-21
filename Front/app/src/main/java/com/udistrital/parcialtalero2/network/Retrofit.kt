package com.udistrital.soviet_paws.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private const val BASE_URL = "http://localhost:3000/"
    private val retrofit: Retrofit by lazy {
         Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val mascotasApi: MascotasApi by lazy {
        retrofit.create(MascotasApi::class.java)
    }
}
