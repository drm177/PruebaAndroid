package com.drm177.pruebaandroid.model

data class BuscadorData(
    var title: String,
    var artist: ArtistData,
    var album: AlbumData
)

data class ArtistData(
    var name: String
)

data class AlbumData(
    var cover: String
)