package com.rafaelbarbosatec.sdk

import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepository

interface RepositorySDK {
    companion object {
        var instance:RepositorySDK? = null
    }
    fun pokemon(): PokemonRepository
    fun authentication():Any?
}