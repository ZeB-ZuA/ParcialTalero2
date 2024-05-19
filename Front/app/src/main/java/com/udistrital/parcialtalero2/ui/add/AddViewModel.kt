package com.udistrital.parcialtalero2.ui.add


import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {

    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> get() = _name
    private val _type = MutableLiveData<String>("")
    val type: LiveData<String> get() = _type
    private val _age = MutableLiveData<Int>(0)
    val age: LiveData<Int> get() = _age
    private val _breed = MutableLiveData<String>("")
    val breed: LiveData<String> get() = _breed
    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> get() = _imageUri

    fun setName(name: String) {
        _name.value = name
    }

    fun setType(type: String) {
        _type.value = type
    }

    fun setAge(age: Int) {
        _age.value = age
    }

    fun setBreed(breed: String) {
        _breed.value = breed
    }

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }

fun printInfo(){
    println("Nombre: "+_name.value)
    println("tipo: "+_type.value)
    println("edad: "+_age.value)
    println("raza: "+_breed.value)
    println("uri: "+_imageUri.value)


}





}