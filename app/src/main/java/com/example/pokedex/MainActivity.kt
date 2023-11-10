package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity()
{
    private lateinit var pokemonList : MutableList<String>   //list of photo URLs
    private lateinit var recyclerViewPokemon: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewPokemon = findViewById<RecyclerView>(R.id.pokemon_recyclerView)
        pokemonList = mutableListOf<String>()   //list of photo URLs

        getPokemonImageURLs()
    }

    private fun getPokemonImageURLs() {
        val client = AsyncHttpClient()

        client["https://pokeapi.co/api/v2/pokemon/ditto", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                val pokemonImageArray = json.jsonArray
                for (i in 0 until pokemonImageArray.length()) {
                    pokemonList.add(pokemonImageArray.getJSONObject(i).getString("url"))
                }
                val pokemonAdapter = PokemonAdapter(pokemonList)
                recyclerViewPokemon.adapter = pokemonAdapter
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            )
            {
                Log.d("Pokemon error", errorResponse)
            }
        }]
    }
}