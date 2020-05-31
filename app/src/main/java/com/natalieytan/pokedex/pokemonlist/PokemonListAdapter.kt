package com.natalieytan.pokedex.pokemonlist

import PokemonListItemViewHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.natalieytan.pokedex.models.Pokemon

class PokemonListAdapter : ListAdapter<Pokemon, PokemonListItemViewHolder>(PokemonListDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListItemViewHolder {
        return PokemonListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PokemonListItemViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }
}

class PokemonListDiffCallBack: DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = (oldItem == newItem)
}