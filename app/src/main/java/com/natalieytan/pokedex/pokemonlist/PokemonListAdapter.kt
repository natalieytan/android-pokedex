package com.natalieytan.pokedex.pokemonlist

import PokemonListItemViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natalieytan.pokedex.R
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
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonListItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PokemonListItemViewHolder, position: Int) {
        val pokemon = data[position]
        holder.pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"
        holder.pokemonName.text = pokemon.name

        val typePrimary = pokemon.type[0]
        val typeSecondary = pokemon.type.getOrNull(1)

        holder.pokemonTypePrimary.text = typePrimary

        when (typeSecondary) {
            null -> holder.pokemonTypeSecondary.visibility = View.GONE
            else -> {
                holder.pokemonTypeSecondary.text = typeSecondary
                holder.pokemonTypeSecondary.visibility = View.VISIBLE
            }
        }
    }
}