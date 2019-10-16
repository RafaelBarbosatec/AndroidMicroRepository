package com.rafaelbarbosatec.sdk.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafaelbarbosatec.sdk.core.converter.Converters
import com.rafaelbarbosatec.sdk.data.pokemon.local.PokemonDAO
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon

@Database(entities = [Pokemon::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SuperDigitalDatabase: RoomDatabase() {
    abstract fun prokemonDAO(): PokemonDAO
}