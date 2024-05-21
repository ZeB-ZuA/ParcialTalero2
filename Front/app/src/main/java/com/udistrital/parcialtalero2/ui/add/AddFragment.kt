package com.udistrital.parcialtalero2.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.udistrital.parcialtalero2.databinding.FragmentAddBinding
import com.udistrital.parcialtalero2.pecistencia.MascotaServicio
import com.udistrital.parcialtalero2.ui.list.ListViewModel


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddViewModel
    private lateinit var mascotaServicio: MascotaServicio
    private lateinit var context: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
        mascotaServicio = MascotaServicio(context)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inicializar ViewModel con las dependencias
        mascotaServicio = MascotaServicio(context)
        viewModel = AddViewModel(mascotaServicio)
        // Ponerle el observer de las variables del viewmodel al las variables del UI(Enlazarlas)
        observeViewModel()
        // Accion del boton Submit
        binding.SubmitButton.setOnClickListener {
            onSubmitButtonClick()
        }
        // eventode  botton para escojer imagenes
        binding.imageButton.setOnClickListener {
            pickImage()
        }
    }
    private fun observeViewModel() {
        viewModel.nombre.observe(viewLifecycleOwner, Observer {
            binding.nametxt.setText(it)
        })

        viewModel.tipo.observe(viewLifecycleOwner, Observer {
            setSpinnerSelection(binding.spinnerType, it)
        })

        viewModel.edad.observe(viewLifecycleOwner, Observer {
            binding.agetxt.setText(it.toString())
        })

        viewModel.raza.observe(viewLifecycleOwner, Observer {
            setSpinnerSelection(binding.spinnerBreed, it)
        })

        viewModel.img.observe(viewLifecycleOwner, Observer { img ->

            val uri = Uri.parse(img)
            binding.imageButton.setImageURI(uri)
        })

    }

    private fun onSubmitButtonClick() {
        viewModel.setName(binding.nametxt.text.toString())
        viewModel.setType(binding.spinnerType.selectedItem.toString())
        viewModel.setAge(binding.agetxt.text.toString().toInt())
        viewModel.setBreed(binding.spinnerBreed.selectedItem.toString())
        viewModel.printInfo()
        viewModel.guardar()
    }

    private fun setSpinnerSelection(spinner: Spinner, value: String) {
        val adapter = spinner.adapter
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i) == value) {
                spinner.setSelection(i)
                break
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            viewModel.setImageUri(imageUri.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}

