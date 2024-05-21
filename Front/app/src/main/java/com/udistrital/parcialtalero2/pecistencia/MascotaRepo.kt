package com.udistrital.parcialtalero2.pecistencia

import com.udistrital.parcialtalero2.model.Mascota

interface MascotaRepo {

    fun guardar(mascota: Mascota)
    fun listar():List<Mascota>
    fun filtrar(name: String?, sortBy: String?): List<Mascota>



}