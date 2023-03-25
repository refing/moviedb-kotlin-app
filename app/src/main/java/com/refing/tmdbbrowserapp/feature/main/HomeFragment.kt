package com.refing.tmdbbrowserapp.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.data.source.Resource
import com.refing.tmdbbrowserapp.core.ui.ListMovieAdapter
import com.refing.tmdbbrowserapp.databinding.FragmentHomeBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding.rvMovies.setHasFixedSize(true)
            binding.rvMovies2.setHasFixedSize(true)
            showRecyclerList()
        }

    }

    private fun showRecyclerList() {
        val listPopularMovieAdapter = ListMovieAdapter()
        listPopularMovieAdapter.onItemClick = { popularselect ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, popularselect)
            startActivity(intent)
        }
        val listUpcomingMovieAdapter = ListMovieAdapter()
        listUpcomingMovieAdapter.onItemClick = { upcomingselect ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, upcomingselect)
            startActivity(intent)
        }

        homeViewModel.popularmovies.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listPopularMovieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.error)
                    }
                }
            }
        }

        homeViewModel.upcomingmovies.observe(viewLifecycleOwner) { up ->
            if (up != null) {
                when (up) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listUpcomingMovieAdapter.setData(up.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = up.message ?: getString(R.string.error)
                    }
                }
            }
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
//            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listPopularMovieAdapter
        }
        with(binding.rvMovies2) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listUpcomingMovieAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}