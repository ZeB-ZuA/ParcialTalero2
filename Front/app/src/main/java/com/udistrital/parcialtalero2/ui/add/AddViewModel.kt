package com.udistrital.parcialtalero2.ui.add


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udistrital.parcialtalero2.model.Mascota
import com.udistrital.parcialtalero2.pecistencia.MascotaServicio
import com.udistrital.parcialtalero2.ui.list.ListViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class AddViewModel (private val mascotaServicio: MascotaServicio): ViewModel() {

    private val _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> get() = _nombre
    private val _tipo = MutableLiveData<String>("")
    val tipo: LiveData<String> get() = _tipo
    private val _edad = MutableLiveData<Int>(0)
    val edad: LiveData<Int> get() = _edad
    private val _raza = MutableLiveData<String>("")
    val raza: LiveData<String> get() = _raza
    private val _img = MutableLiveData<String?>()
    val img: LiveData<String?> get() = _img

    fun setName(name: String) {
        _nombre.value = name
    }

    fun setType(type: String) {
        _tipo.value = type
    }

    fun setAge(age: Int) {
        _edad.value = age
    }

    fun setBreed(breed: String) {
        _raza.value = breed
    }

    fun setImageUri(uri: String) {
        _img.value = uri
    }

fun printInfo(){
    println("Nombre: "+_nombre.value)
    println("tipo: "+_tipo.value)
    println("edad: "+_edad.value)
    println("raza: "+_raza.value)
    println("uri: "+_img.value)


}


    fun guardar() {
        viewModelScope.launch {
            val nombre = _nombre.value ?:""
            val tipo = _tipo.value ?:""
            val edad = _edad.value ?:0
            val raza = _raza.value ?:""
            val img = _img.value
            println("Guardando mascota:")
            println("Nombre: $nombre")
            println("Tipo: $tipo")
            println("Edad: $edad")
            println("Raza: $raza")
            println("Imagen: $img")
            if (img != null) {
                val file = File(img)
                val mascota = Mascota(
                    name = nombre,
                    type = tipo,
                    age = edad,
                    breed =  raza,
                    image = img
                )
                mascotaServicio.guardar(mascota)
                println("Mascota guardada exitosamente.")


            } else {
                println("sin imagen")
            }
        }
    }






}