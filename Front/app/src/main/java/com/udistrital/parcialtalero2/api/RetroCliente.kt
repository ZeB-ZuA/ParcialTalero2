package com.udistrital.parcialtalero2.api

import com.udistrital.parcialtalero2.model.Mascota
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroCliente {

    private const val BASE_URL = "http://localhost:3000/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val mascotaApi: MascotaApi by lazy {
        retrofit.create(MascotaApi::class.java)
    }


}