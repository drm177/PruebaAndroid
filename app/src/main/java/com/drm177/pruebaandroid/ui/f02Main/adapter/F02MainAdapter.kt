package com.drm177.pruebaandroid.ui.f02Main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drm177.pruebaandroid.databinding.F02MainCardviewBinding
import com.drm177.pruebaandroid.model.BuscadorData
import com.squareup.picasso.Picasso

class F02MainAdapter(
    private val resultadoList: List<BuscadorData>,
    private val mBotonListener: BotonListener
) : RecyclerView.Adapter<F02MainAdapter.ViewHolder>() {
    private var boton = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = F02MainCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mBotonListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(resultadoList[position]){
                binding.tvArtista.text = this.artist.name
                binding.tvCancion.text = this.title
                val urlImagenAlbum: String = this.album.cover
                Picasso.get().load(urlImagenAlbum).fit().centerCrop().into(binding.imgAlbum)
            }
        }
    }

    override fun getItemCount(): Int {
        return resultadoList.size
    }

    inner class ViewHolder(val binding: F02MainCardviewBinding, var botonListener: BotonListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(v: View?) {
            boton = absoluteAdapterPosition
            notifyDataSetChanged()

            val artista = binding.tvArtista.text.toString()
            val titulo = binding.tvCancion.text.toString()

            botonListener.botonClick(artista, titulo)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface BotonListener {
        fun botonClick(artista: String, titulo: String)
    }
}