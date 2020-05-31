package com.natalieytan.pokedex.pokemondetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.natalieytan.pokedex.R
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        if (intent.hasExtra(Intent.EXTRA_INDEX)) {
            when (val pokemonId = intent.getIntExtra(Intent.EXTRA_INDEX, 0)) {
                0 -> dummyText.text = "Invalid pokemon id. Error logging time"
                else -> dummyText.text = "Pokemon id $pokemonId selected"
            }
        } else {
            dummyText.text = "no intent index bleep blop"
        }
    }
}