package com.drm177.pruebaandroid.ui.f02Main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drm177.pruebaandroid.R
import com.drm177.pruebaandroid.core.RetrofitHelper.service
import com.drm177.pruebaandroid.databinding.F02MainViewBinding
import com.drm177.pruebaandroid.model.BuscadorData
import com.drm177.pruebaandroid.model.LyricsOvhBuscadorModel
import com.drm177.pruebaandroid.model.LyricsOvhLetraModel
import com.drm177.pruebaandroid.ui.f02Main.adapter.F02MainAdapter
import com.drm177.pruebaandroid.ui.f02Main.fragment.F02MainFragment
import com.drm177.pruebaandroid.util.common.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class F02MainViewModel @Inject constructor(
    private val router: F02MainRouter,
    private val util: Util
    ) : ViewModel(), F02MainAdapter.BotonListener {

    @SuppressLint("StaticFieldLeak")
    private lateinit var activity: Activity
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private lateinit var binding: F02MainViewBinding

    private lateinit var f02MainAdapter: F02MainAdapter
    private lateinit var resultadoList : List<BuscadorData>

    var letra = ""
    var tituloCancion = ""

    //Funciones
    fun inicio(act: Activity, cxt: Context, bind: F02MainViewBinding){
        activity = act
        context = cxt
        binding = bind
        util.appOE(activity)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        binding.rvList.layoutManager = layoutManager
    }

    private fun initFragment(title: String, texto: String){
        letra = texto
        tituloCancion = title
        val activity = context as AppCompatActivity
            val f02MainFragment = F02MainFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.clField, f02MainFragment)
                .addToBackStack(null).commit()
    }

    //Network Busca cancion
    fun busqueda(buscar: String){
        quitarTeclado()
        progressBar(true)
        service.buscarTitulo(buscar).enqueue(
            object: retrofit2.Callback<LyricsOvhBuscadorModel> {
                override fun onResponse(call: Call<LyricsOvhBuscadorModel>, response: Response<LyricsOvhBuscadorModel>) {
                    procesaResultado(response)
                }

                override fun onFailure(call: Call<LyricsOvhBuscadorModel>, t: Throwable) {
                    progressBar(false)
                    msgError(true, "Network error")
                }
            }
        )
    }

    fun procesaResultado(response: Response<LyricsOvhBuscadorModel>){
        val body = response.body()
        val data = body?.data
        val total = body?.total
        when {
            total == 0 -> {
                msgError(true, "No lyrics found")
            }
            data != null -> {
                msgError(false, "")
                resultadoList = data
                f02MainAdapter = F02MainAdapter(resultadoList, this)
                binding.rvList.adapter = f02MainAdapter
            }
            else -> {
                msgError(true, "Request failed")
            }
        }
        progressBar(false)
    }

    //Network Carga letra de cancion
    private fun cargaLetra(artista: String, titulo: String){
        progressBar(true)
        service.search(artista, titulo).enqueue(
                object: retrofit2.Callback<LyricsOvhLetraModel> {
                    override fun onResponse(call: Call<LyricsOvhLetraModel>, response: Response<LyricsOvhLetraModel>) {
                        val body = response.body()

                        val texto = body?.lyrics ?: "No lyrics found"
                        initFragment(titulo, texto)
                        progressBar(false)
                    }

                    override fun onFailure(call: Call<LyricsOvhLetraModel>, t: Throwable) {
                        progressBar(false)
                        msgError(true, "Network error")
                    }
                }
            )
    }

    fun quitarTeclado(){
        binding.etBuscar.isEnabled = false
        binding.etBuscar.isEnabled = true
    }

    fun progressBar(valor: Boolean){
        if (valor) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    fun msgError(valor: Boolean, msg: String){
        if (valor) {
            binding.tvMsg.visibility = View.VISIBLE
            binding.tvMsg.text = msg
        } else {
            binding.tvMsg.visibility = View.GONE
        }
    }

    override fun botonClick(artista: String, titulo: String) {
        cargaLetra(artista, titulo)
    }
}