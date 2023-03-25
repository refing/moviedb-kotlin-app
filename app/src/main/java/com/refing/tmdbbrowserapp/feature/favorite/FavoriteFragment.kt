package com.refing.tmdbbrowserapp.feature.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.refing.tmdbbrowserapp.core.ui.ListMovieAdapter
import com.refing.tmdbbrowserapp.core.ui.ViewModelFactory
import com.refing.tmdbbrowserapp.databinding.FragmentFavoriteBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val listMovieAdapter = ListMovieAdapter()
            listMovieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) { movie ->
                binding.progressBar.visibility = View.GONE
                listMovieAdapter.setData(movie)
            }

            with(binding.rvFavorites) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listMovieAdapter
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}