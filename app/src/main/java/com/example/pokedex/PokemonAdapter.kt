package com.example.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PokemonAdapter(val pokemonList:MutableList<String>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val PokemonImage: ImageView

        init {
            PokemonImage = view.findViewById(R.id.pokemon_image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
       return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}