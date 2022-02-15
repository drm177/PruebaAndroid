package com.drm177.pruebaandroid.ui.f01Launch

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.drm177.pruebaandroid.databinding.F01LaunchViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class F01LaunchView : AppCompatActivity() {

    //Propiedades
    private lateinit var binding: F01LaunchViewBinding
    private val viewModel: F01LaunchViewModel by viewModels()

    //Inicializacion
    private fun init(){
        viewModel.inicio(this, this)
    }

    //Body
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = F01LaunchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
}