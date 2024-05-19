package com.udistrital.parcialtalero2.api

import com.udistrital.parcialtalero2.model.Mascota
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MascotaApi {
    @GET("mascotas")
    suspend fun listar(): List<Mascota>

    @Multipart
    @POST("pets")
    suspend fun guardar(
        @Part img: MultipartBody.Part,
        @Part("type") tipo: RequestBody,
        @Part("name") nombre: RequestBody,
        @Part("age") edad: RequestBody,
        @Part("breed") raza: RequestBody
    ): Mascota
    @POST("filterPets")
    suspend fun NombreYraza(nombre: String?, raza: String?): List<Mascota>



}