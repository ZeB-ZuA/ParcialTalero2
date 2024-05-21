package com.udistrital.parcialtalero2.ui.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.udistrital.parcialtalero2.model.Mascota
import com.udistrital.parcialtalero2.pecistencia.MascotaServicio
import kotlinx.coroutines.launch


class ListViewModel : ViewModel() {

    private lateinit var repo: MascotaServicio
    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascota: LiveData<List<Mascota>> = _mascotas

    fun init(context: Context) {
        repo = MascotaServicio(context)
        cargarMascotas()
    }

     fun cargarMascotas() {
        viewModelScope.launch {
            val mascotas = repo.listar()
            _mascotas.value = mascotas
        }
    }
    fun filtrar(nombre: String?, ordenar: String?) {
        viewModelScope.launch {
            val mascotasFiltradas = repo.filtrar(nombre, ordenar)
            _mascotas.value = mascotasFiltradas
        }
    }


}


