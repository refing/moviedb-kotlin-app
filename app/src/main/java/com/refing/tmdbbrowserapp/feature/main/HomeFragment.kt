package com.refing.tmdbbrowserapp.feature.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.data.source.Resource
import com.refing.tmdbbrowserapp.core.ui.ListMovieAdapter
import com.refing.tmdbbrowserapp.core.ui.ViewModelFactory
import com.refing.tmdbbrowserapp.databinding.FragmentFavoriteBinding
import com.refing.tmdbbrowserapp.databinding.FragmentHomeBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import com.refing.tmdbbrowserapp.feature.favorite.FavoriteViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

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
        val listMovieAdapter = ListMovieAdapter()
        listMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        homeViewModel.movies.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listMovieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.error)
                    }
                }
            }
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
        with(binding.rvMovies2) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}