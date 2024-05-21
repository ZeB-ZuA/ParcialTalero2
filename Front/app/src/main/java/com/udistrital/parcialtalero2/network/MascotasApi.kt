package com.udistrital.soviet_paws.network

import com.udistrital.parcialtalero2.model.Mascota
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface MascotasApi {

    @GET("pets")
    suspend fun listar(): List<Mascota>
    @GET("pet")
    suspend fun get(): Mascota

    @Multipart
    @POST("pets")
    suspend fun guardar(
        @Part image: MultipartBody.Part,
        @Part("type") type: RequestBody,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("breed") breed: RequestBody
    ): Mascota
    @GET("/pets/filter")
    suspend fun filtrar(@Query("name") name: String?, @Query("sortBy") sortBy: String?): List<Mascota>

}