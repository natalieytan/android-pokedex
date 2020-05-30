package com.natalieytan.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natalieytan.pokedex.network.ApiStatus
import com.natalieytan.pokedex.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val _pokemonList = MutableLiveData<String>()
    val pokemonList: LiveData<String>
        get() = _pokemonList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPokemon()
    }

    private fun getPokemon() {
        coroutineScope.launch {
            _status.value = ApiStatus(ApiStatus.Status.LOADING, null)
            try {
                var pokemonList = PokemonApi.retrofitService.getPokemonList()
                _pokemonList.value = "Success ${pokemonList.size} pokemon"
                _status.value = ApiStatus(ApiStatus.Status.DONE, null)
            } catch (t: Throwable) {
                _status.value = ApiStatus(ApiStatus.Status.ERROR, t.message)
                _pokemonList.value = "Error ${t.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}