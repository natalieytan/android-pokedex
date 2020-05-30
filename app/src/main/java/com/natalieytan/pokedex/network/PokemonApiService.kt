package com.natalieytan.pokedex.network

import com.natalieytan.pokedex.models.PokemonSummary
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


data class ApiStatus(val status: Status, val message: String?) {
    enum class Status { LOADING, ERROR, DONE }
}

private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("https://gist.githubusercontent.com/natalieytan/eb3bac2b50377207dd0e7c117068b3b2/raw/50214e592c20c89f58b84e9dbdd29f47a6750219/pokemon.json")
    fun getPokemonList():
            Call<List<PokemonSummary>>
}

object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}