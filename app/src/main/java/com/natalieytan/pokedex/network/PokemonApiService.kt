package com.natalieytan.pokedex.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


data class ApiStatus(val status: Status, val message: String?) {
    enum class Status { LOADING, ERROR, DONE }
}

private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("https://gist.githubusercontent.com/natalieytan/eb3bac2b50377207dd0e7c117068b3b2/raw/50214e592c20c89f58b84e9dbdd29f47a6750219/pokemon.json")
    fun getPokemonList():
            Call<String>
}

object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}