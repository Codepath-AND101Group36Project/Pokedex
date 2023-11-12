package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadButton = findViewById<Button>(R.id.load_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val pokeList = mutableListOf<JSONObject>() //List of pokemon object
        val pokeAdapter = PokeAdapter(pokeList)
        loadButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                //Call API
                getPokeList(pokeList, recyclerView, pokeAdapter)
                loadButton.text = "Load 5 more"
            }
        })
        deleteButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                //Delete pokeList's items
                pokeAdapter.deleteData()
            }
        })
    }


    private fun getPokemonData(pokeList: MutableList<JSONObject>, randomNumber: Int, onComplete: () -> Unit) {
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["limit"] = "5"
        params["page"] = "0"

        client["https://pokeapi.co/api/v2/pokemon/$randomNumber/", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                pokeList.add(json.jsonObject)
                onComplete()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                onComplete()
            }
        }]
    }

    //Get random list of 5 items
    private fun getPokeList(pokeList: MutableList<JSONObject>, recyclerView: RecyclerView, pokeAdapter: PokeAdapter) {
        var completedCalls = 0
        val expectedCalls = 5

        for (i in 1..expectedCalls) {
            getPokemonData(pokeList, (1..898).random()) {
                completedCalls++
                if (completedCalls == expectedCalls) {
                    recyclerView.adapter = pokeAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
                Log.d("List", pokeList.size.toString())
            }
        }
    }
}