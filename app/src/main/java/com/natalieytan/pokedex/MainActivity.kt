package com.natalieytan.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by lazy {
        ViewModelProvider(this).get(PokemonListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemonList.observe(this, Observer { pokemonList ->
            dummyText.text = pokemonList
        })
    }
}