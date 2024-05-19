package com.udistrital.parcialtalero2.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Details Fragment"
    }
    val text: LiveData<String> = _text
}