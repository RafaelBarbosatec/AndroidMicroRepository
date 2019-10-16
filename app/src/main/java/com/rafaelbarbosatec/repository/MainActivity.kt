package com.rafaelbarbosatec.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rafaelbarbosatec.sdk.RepositoryBuilder
import com.rafaelbarbosatec.sdk.RepositorySDK
import com.rafaelbarbosatec.sdk.core.extensions.read
import com.rafaelbarbosatec.sdk.core.response.ResponseError
import com.rafaelbarbosatec.sdk.core.response.ResponseSuccess
import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var superDigital: RepositorySDK? = null

    private val pokemonRepository: PokemonRepository? by lazy {
        superDigital?.pokemon()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLIB()

        progress_circular.visibility = View.VISIBLE
        pokemonRepository?.pokemons { any ->
            any.read({
                tv.text = ("Count pokemons: ${it.size}")
            },{
                tv.text = it.errorMessage
            })
            progress_circular.visibility = View.GONE
        }

    }

    private fun initLIB() {
        superDigital = RepositoryBuilder(this)
            .baseUrl("https://vortigo.blob.core.windows.net")
            .debugable(true)
            .build()
    }

    override fun onDestroy() {
        pokemonRepository?.destroy()
        super.onDestroy()
    }
}
