package com.natalieytan.pokedex.pokemonlist

import PokemonListItemViewHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natalieytan.pokedex.models.Pokemon

class PokemonListAdapter : RecyclerView.Adapter<PokemonListItemViewHolder>() {
    var data = listOf<Pokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListItemViewHolder {
        return PokemonListItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PokemonListItemViewHolder, position: Int) {
        val pokemon = data[position]
        holder.bind(pokemon)
    }
}