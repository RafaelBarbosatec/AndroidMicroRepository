package com.rafaelbarbosatec.sdk.data.pokemon.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pokemons")
data class Pokemon (
    @PrimaryKey(autoGenerate = true)
    val idRoom: Long,
    val id: Int,
    val detailPageURL : String,
    val weight : Double,
    val number : Int,
    val height : Int,
    val collectibles_slug : String,
    val featured : Boolean,
    val slug : String,
    val name : String,
    val thumbnailAltText : String,
    val thumbnailImage : String,
    val type: ArrayList<String>
):Serializable
