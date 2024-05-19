package com.udistrital.parcialtalero2.model

import android.net.Uri

data class Mascota(

    val nombre: String,
    val tipo: String,
    val edad: String,
    val raza: String,
    val img: Uri
)
