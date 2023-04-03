package com.refing.tmdbbrowserapp.feature.home


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.ui.ListMovieAdapter
import com.refing.tmdbbrowserapp.databinding.ActivityMainBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList


class MainActivity : AppCompatActivity(){

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies2.setHasFixedSize(true)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_drawer, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                val uri = Uri.parse("tmdbbrowserapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> true
        }
    }
    private fun showRecyclerList() {



        homeViewModel.popularmovies.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is com.refing.tmdbbrowserapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.refing.tmdbbrowserapp.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val listPopularMovieAdapter = ListMovieAdapter(movie.data as ArrayList<Movie>)
                        listPopularMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: Movie) {
                                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                                startActivity(intent)
                            }
                        })
                        with(binding.rvMovies) {
                            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                            setHasFixedSize(true)
                            adapter = listPopularMovieAdapter
                        }
                    }
                    is com.refing.tmdbbrowserapp.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        homeViewModel.upcomingmovies.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is com.refing.tmdbbrowserapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.refing.tmdbbrowserapp.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val listUpcomingMovieAdapter = ListMovieAdapter(movie.data as ArrayList<Movie>)
                        listUpcomingMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: Movie) {
                                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                                startActivity(intent)
                            }
                        })
                        with(binding.rvMovies2) {
                            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                            setHasFixedSize(true)
                            adapter = listUpcomingMovieAdapter
                        }
                    }
                    is com.refing.tmdbbrowserapp.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }


    }


}