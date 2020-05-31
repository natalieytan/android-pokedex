package com.natalieytan.pokedex.pokemonlist

import PokemonListItemViewHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.natalieytan.pokedex.models.Pokemon

class PokemonListAdapter(
    private val pokemonListItemClickListener: PokemonListItemViewHolder.PokemonListItemClickListener
) : ListAdapter<Pokemon, PokemonListItemViewHolder>(PokemonListDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListItemViewHolder {
        return PokemonListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PokemonListItemViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon, pokemonListItemClickListener)
    }
}

class PokemonListDiffCallBack : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        (oldItem == newItem)
}