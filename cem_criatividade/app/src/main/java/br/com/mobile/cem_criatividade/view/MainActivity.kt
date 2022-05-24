package br.com.mobile.cem_criatividade.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile.cem_criatividade.R
import br.com.mobile.cem_criatividade.api.PokemonRepository
import br.com.mobile.cem_criatividade.domain.Pokemon
import br.com.mobile.cem_criatividade.domain.PokemonType
import br.com.mobile.cem_criatividade.viewmodel.PokemonViewModel
import br.com.mobile.cem_criatividade.viewmodel.PokemonViewModelFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.rvPokemons)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)

    }
}
