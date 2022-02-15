package com.drm177.pruebaandroid.ui.f02Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.drm177.pruebaandroid.databinding.F02MainViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class F02MainView : AppCompatActivity(), View.OnClickListener {

    //Propiedades
    private lateinit var binding: F02MainViewBinding
    private val viewModel: F02MainViewModel by viewModels()

    //Inicializacion
    private fun init(){
        viewModel.inicio(this, this, binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = F02MainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        onClickBotones()
    }

    private fun onClickBotones() {
        binding.clField.setOnClickListener(this)
        binding.btnBuscar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.clField -> {
                viewModel.quitarTeclado()
            }
            binding.btnBuscar -> {
                viewModel.busqueda(binding.etBuscar.text.toString())
            }
        }
    }
}