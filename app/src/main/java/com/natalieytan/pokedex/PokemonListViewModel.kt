package com.natalieytan.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natalieytan.pokedex.models.PokemonSummary
import com.natalieytan.pokedex.network.ApiStatus
import com.natalieytan.pokedex.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListViewModel : ViewModel() {
    private val _pokemonList = MutableLiveData<String>()
    val pokemonList: LiveData<String>
        get() = _pokemonList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getPokemon()
    }

    private fun getPokemon() {
        _status.value = ApiStatus(ApiStatus.Status.LOADING, null)

        PokemonApi.retrofitService.getPokemonList()
            .enqueue(object : Callback<List<PokemonSummary>> {
                override fun onFailure(call: Call<List<PokemonSummary>>, t: Throwable) {
                    _status.value = ApiStatus(ApiStatus.Status.ERROR, t.message)
                    _pokemonList.value = "Error ${t.message}"
                }

                override fun onResponse(
                    call: Call<List<PokemonSummary>>,
                    response: Response<List<PokemonSummary>>
                ) {
                    _status.value = ApiStatus(ApiStatus.Status.DONE, null)
                    _pokemonList.value = "Success ${response.body()?.size} pokemon"
                }
            })
    }
}