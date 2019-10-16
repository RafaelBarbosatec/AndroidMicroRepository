package com.rafaelbarbosatec.sdk

import android.content.Context
import com.rafaelbarbosatec.sdk.core.db.DatabaseCreate
import com.rafaelbarbosatec.sdk.core.service.ServiceGenerator
import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepository
import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepositoryImpl
import java.util.concurrent.Executors

class RepositorySDKImpl(context: Context, URL_BASE: String, debugable: Boolean) : RepositorySDK {

    private val serviceGenerator = ServiceGenerator(URL_BASE,debugable)
    private val database = DatabaseCreate(context).create()
    private val executor = Executors.newFixedThreadPool(2)

    init {
        RepositorySDK.instance = this
    }
    /**
     * Módulo de pokemons
     * No contexto RepositorySDK poderia ser: "cards","transfer","profile",etc
     */
    override fun pokemon(): PokemonRepository {
        return PokemonRepositoryImpl(
            serviceGenerator.generate(),
            database.prokemonDAO(),
            executor
        )
    }

    /**
     * Somente exemplo de criacao do modulo responsável pela autenticação
     * Aqui retornaria o módulo responsável por tal
     */
    override fun authentication(): Any? {
        return null
    }

}