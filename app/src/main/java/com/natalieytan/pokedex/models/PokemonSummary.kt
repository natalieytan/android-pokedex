package com.natalieytan.pokedex.models

data class PokemonSummary(
    val id: Int,
    val sprites: Sprites,
    val name: String,
    val type: List<String>
) {
    data class Sprites(
        val normal: String,
        val large: String,
        val animated: String
    )
}