package com.udistrital.parcialtalero2.ui.add

import android.app.Activity
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


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

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
        viewModel.name.observe(viewLifecycleOwner, Observer {
            binding.nametxt.setText(it)
        })

        viewModel.type.observe(viewLifecycleOwner, Observer {
            setSpinnerSelection(binding.spinnerType, it)
        })

        viewModel.age.observe(viewLifecycleOwner, Observer {
            binding.agetxt.setText(it.toString())
        })

        viewModel.breed.observe(viewLifecycleOwner, Observer {
            setSpinnerSelection(binding.spinnerBreed, it)
        })

        viewModel.imageUri.observe(viewLifecycleOwner, Observer {
           // Cambiar la imagen del imageButton con la uri seleccionada
            binding.imageButton.setImageURI(it)
        })
    }

    private fun onSubmitButtonClick() {
        viewModel.setName(binding.nametxt.text.toString())
        viewModel.setType(binding.spinnerType.selectedItem.toString())
        viewModel.setAge(binding.agetxt.text.toString().toInt())
        viewModel.setBreed(binding.spinnerBreed.selectedItem.toString())
        viewModel.printInfo()
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
            viewModel.setImageUri(imageUri)
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

