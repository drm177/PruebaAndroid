package com.drm177.pruebaandroid.model

data class LyricsOvhBuscadorModel (
    val data: List<BuscadorData>,
    val total: Int
    )

data class LyricsOvhLetraModel (
    val lyrics: String? = null,
    val error: String? = null
)