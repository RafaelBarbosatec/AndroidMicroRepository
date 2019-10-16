package com.rafaelbarbosatec.sdk.data.pokemon.remote

import androidx.lifecycle.LiveData
import com.rafaelbarbosatec.sdk.core.response.ResponseAny
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.rafaelbarbosatec.sdk.data.pokemon.model.ResultType
import retrofit2.http.GET

interface PokemonApi{
    @GET("files/pokemon/data/pokemons.json")
    fun getPokemons(): LiveData<ResponseAny<ArrayList<Pokemon>>>

    @GET("files/pokemon/data/types.json")
    fun getTypes(): LiveData<ResponseAny<ResultType>>

}
