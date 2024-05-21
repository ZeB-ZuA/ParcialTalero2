package com.udistrital.parcialtalero2.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.udistrital.parcialtalero2.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.init(requireContext())
        viewModel.cargarMascotas()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.mascota.observe(viewLifecycleOwner, Observer { mascotas ->
            recyclerView.adapter = MascotaAdapter(mascotas, requireContext())
            mascotas.forEach { mascota ->
                Log.d("ListFragment", "Mascota: ${mascota.name} \n URI: ${mascota.image} RAZA: ${mascota.breed} EDAD ${mascota.age}")
            }
        })
        binding.button.setOnClickListener {
            val nombre = binding.searchEditText.text.toString()
            val ordenar = binding.spinner.selectedItem.toString()
            println("FILTRAR POR ${nombre}, ORDENAR POR ${ordenar}")
            viewModel.filtrar(nombre, ordenar)
        }
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

