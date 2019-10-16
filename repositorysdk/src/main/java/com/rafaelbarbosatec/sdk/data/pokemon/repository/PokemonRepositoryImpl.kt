package com.rafaelbarbosatec.sdk.data.pokemon.repository

import com.rafaelbarbosatec.sdk.core.extensions.listen
import com.rafaelbarbosatec.sdk.core.livecycle.RepositoryLiveCycle
import com.rafaelbarbosatec.sdk.core.response.ResponseAny
import com.rafaelbarbosatec.sdk.core.response.ResponseError
import com.rafaelbarbosatec.sdk.core.response.ResponseSuccess
import com.rafaelbarbosatec.sdk.data.pokemon.local.PokemonDAO
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.rafaelbarbosatec.sdk.data.pokemon.model.TypePokemon
import com.rafaelbarbosatec.sdk.data.pokemon.remote.PokemonApi
import java.util.concurrent.Executor

/**
 * Implementação do repositório
 * Classe responsável por obter os dados solicitados, que podem vim
 * por banco ou chamada de uma API.
 *
 * Nesse exemplo utilizamos um banco local para salvar os dados procisando realizar somente a primeira
 * chamada de API e todas as outras chamadas são respondidas pelo banco(Room).
 *
 * Mas poderia ser somente solicitação de dados atravez da API, ou qualquer outra estratégia,
 * vai depender da necessidade.
 *
 */
class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi,
    private val pokemonDb: PokemonDAO,
    private val executor: Executor
) : PokemonRepository, RepositoryLiveCycle() {

    private var pokemonsLocal = ArrayList<Pokemon>()

    override fun pokemons(pokemons: (ResponseAny<ArrayList<Pokemon>>) -> Unit) {
        pokemonDb
            .all()
            .listen(this) {
                if (it.isNotEmpty()) {
                    pokemonsLocal = ArrayList(it)
                    val result = ResponseSuccess(pokemonsLocal)
                    result.fromDb = true
                    pokemons(result)
                } else {
                    pokemonsServer(pokemons)
                }
            }
    }

    override fun types(types: (ResponseAny<ArrayList<TypePokemon>>) -> Unit) {
       pokemonApi
           .getTypes()
           .listen(this) {
               when(it){
                   is ResponseSuccess -> types(ResponseSuccess(it.body.results))
                   is ResponseError -> types(ResponseError(it.errorCode,it.errorMessage))
               }
           }
    }

    private fun pokemonsServer(pokemons: (ResponseAny<ArrayList<Pokemon>>) -> Unit) {
        pokemonApi
            .getPokemons()
            .listen(this) {
                when(it){
                    is ResponseSuccess -> {
                        val data = ArrayList(it.body)
                        insertPokemonsDb(ArrayList(data))
                        if(pokemonsLocal.isEmpty())
                            pokemons(ResponseSuccess(data))
                    }
                    else -> pokemons(it)
                }
            }
    }

    private fun insertPokemonsDb(pokemons: ArrayList<Pokemon>) {
        executor.execute {
            pokemonDb.deleteAll()
            pokemonDb.insert(pokemons)
        }
    }

}