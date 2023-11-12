package com.example.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONObject

class PokeAdapter(private val pokeList: MutableList<JSONObject>) : RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {
    // Adapter code goes here
    class PokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage: ImageView
        val pokeName: TextView
        val pokeHeight: TextView
        val pokeWeight: TextView
        init {
            pokeImage = view.findViewById(R.id.pokeImage)
            pokeName = view.findViewById(R.id.pokeName)
            pokeHeight = view.findViewById(R.id.pokeHeight)
            pokeWeight = view.findViewById(R.id.pokeWeight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeAdapter.PokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val pokeObject = pokeList[position]
        val imageURL = pokeObject.getJSONObject("sprites").getString("front_default")
        Log.d("image", imageURL)

        Picasso.get()
            .load(imageURL)
            .resize(500, 500)
            .centerInside()
            .into(holder.pokeImage)
        holder.pokeName.text = "Name: " + pokeObject.getString("name")
        holder.pokeHeight.text = "Height" + pokeObject.getString("height")
        holder.pokeWeight.text = "Weight" + pokeObject.getString("weight")
    }

    fun deleteData() {
        pokeList.clear()
        notifyDataSetChanged()
    }
}