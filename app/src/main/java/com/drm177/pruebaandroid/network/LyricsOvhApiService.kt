package com.drm177.pruebaandroid.network

import com.drm177.pruebaandroid.model.LyricsOvhBuscadorModel
import com.drm177.pruebaandroid.model.LyricsOvhLetraModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsOvhApiService {
    @GET("suggest/{search}")
    fun buscarTitulo(
        @Path("search") title: String
    ): Call<LyricsOvhBuscadorModel>

    @GET("v1/{artist}/{title}")
    fun search(
        @Path("artist") artist: String,
        @Path("title") title: String
    ): Call<LyricsOvhLetraModel>
}