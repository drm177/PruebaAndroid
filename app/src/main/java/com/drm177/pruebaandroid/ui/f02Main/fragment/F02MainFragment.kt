package com.drm177.pruebaandroid.ui.f02Main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.drm177.pruebaandroid.R
import com.drm177.pruebaandroid.databinding.F02MainFragmentBinding
import com.drm177.pruebaandroid.ui.f02Main.F02MainViewModel

class F02MainFragment : Fragment() {

    private val viewModel: F02MainViewModel by activityViewModels()

    private var _binding: F02MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = F02MainFragmentBinding.inflate(inflater, container, false)
        binding.tvLetra.text = viewModel.letra
        binding.tvTituloCancion.text = viewModel.tituloCancion
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}