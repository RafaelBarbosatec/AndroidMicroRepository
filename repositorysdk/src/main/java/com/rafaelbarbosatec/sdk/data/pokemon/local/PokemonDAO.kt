package com.rafaelbarbosatec.sdk.data.pokemon.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon

@Dao
interface PokemonDAO{

    @Query("SELECT * FROM pokemons")
    fun all(): LiveData<List<Pokemon>>

    @Insert
    fun insert(items: List<Pokemon>)

    @Query("DELETE FROM pokemons")
    fun deleteAll()
}