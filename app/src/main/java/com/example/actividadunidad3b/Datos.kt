package com.example.actividadunidad3b

import java.io.Serializable

data class PlayList(
    val fotoAlbum: String,
    val nombrePlayList: String,
    val seguidores: String,
    val canciones: List<Canciones>

) : Serializable

data class Canciones(
    val fotoCancion: String,
    val tituloCancion: String,
    val nombreArtista: String,
)

data class PlayListResponse(
    val `data`: List<PlayList>
)

