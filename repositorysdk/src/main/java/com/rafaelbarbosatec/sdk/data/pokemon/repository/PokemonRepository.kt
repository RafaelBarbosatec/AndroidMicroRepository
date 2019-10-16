package com.rafaelbarbosatec.sdk.data.pokemon.repository

import com.rafaelbarbosatec.sdk.core.livecycle.Repository
import com.rafaelbarbosatec.sdk.core.response.ResponseAny
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.rafaelbarbosatec.sdk.data.pokemon.model.TypePokemon

interface PokemonRepository: Repository {
    fun pokemons(pokemons: (ResponseAny<ArrayList<Pokemon>>) -> Unit)
    fun types(types: (ResponseAny<ArrayList<TypePokemon>>) -> Unit)
}