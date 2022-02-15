package com.drm177.pruebaandroid.core

import com.drm177.pruebaandroid.network.LyricsOvhApiService
import com.drm177.pruebaandroid.util.common.Parametros
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Parametros.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: LyricsOvhApiService = getRetrofit().create(LyricsOvhApiService::class.java)
}