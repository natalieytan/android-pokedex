package com.natalieytan.pokedex

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.natalieytan.pokedex.network.ApiStatus
import com.natalieytan.pokedex.pokemonlist.PokemonListAdapter
import com.natalieytan.pokedex.pokemonlist.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var toast: Toast? = null

    private val viewModel: PokemonListViewModel by lazy {
        ViewModelProvider(this).get(PokemonListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonItemClickListener =
            PokemonListItemViewHolder.PokemonListItemClickListener { pokemonId ->
                toast?.cancel()
                toast =
                    Toast.makeText(this, "Pokemon with id $pokemonId selected", Toast.LENGTH_SHORT)
                toast?.show()
            }

        val adapter = PokemonListAdapter(pokemonItemClickListener)
        pokemonList.adapter = adapter

        viewModel.status.observe(this, Observer { apiStatus ->
            when (apiStatus.status) {
                ApiStatus.Status.LOADING -> {
                    loader.visibility = View.VISIBLE
                    error.visibility = View.GONE
                    pokemonList.visibility = View.GONE
                }
                ApiStatus.Status.ERROR -> {
                    loader.visibility = View.GONE
                    error.visibility = View.VISIBLE
                    pokemonList.visibility = View.GONE
                }
                ApiStatus.Status.DONE -> {
                    loader.visibility = View.GONE
                    error.visibility = View.GONE
                    pokemonList.visibility = View.VISIBLE
                }
            }
        })

        viewModel.pokemonList.observe(this, Observer { adapter.submitList(it) })
    }
}