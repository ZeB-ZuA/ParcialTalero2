package com.udistrital.parcialtalero2.pecistencia

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udistrital.parcialtalero2.model.Mascota
import com.udistrital.soviet_paws.network.Retrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class MascotaServicio(private val context: Context) : MascotaRepo {

    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascotas: LiveData<List<Mascota>> = _mascotas
    override fun guardar(mascota: Mascota) {
        GlobalScope.launch {
            val uri = Uri.parse(mascota.image)
            val inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream != null) {
                val image = MultipartBody.Part.createFormData(
                    "image",
                    "image.jpg",
                    inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
                )

                val tipo = RequestBody.create("text/plain".toMediaTypeOrNull(), mascota.type)
                val nombre = RequestBody.create("text/plain".toMediaTypeOrNull(), mascota.name)
                val edad =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), mascota.age.toString())
                val raza = RequestBody.create("text/plain".toMediaTypeOrNull(), mascota.breed)

                try {
                    val r = Retrofit.mascotasApi.guardar(
                        image, tipo, nombre, edad, raza
                    )
                } catch (e: Exception) {
                    println("Error al guardar la mascota: ${e.message}")
                }
            } else {
                println("No se pudo abrir el InputStream para la imagen")
            }
        }
    }


    override fun listar(): List<Mascota> {
        return runBlocking {
            val r = Retrofit.mascotasApi.listar()
            r
        }
    }


    override fun filtrar(name: String?, sortBy: String?): List<Mascota> {
        return runBlocking {
            try {
                val mascotasFiltradas = Retrofit.mascotasApi.filtrar(name, sortBy)
                Log.d("MascotaServicio", "se filtran")

                mascotasFiltradas

            } catch (e: Exception) {
                Log.e("MascotaServicio", "Error al filtrar las mascotas: ${e.message}")
                emptyList()
            }
        }
    }
}


