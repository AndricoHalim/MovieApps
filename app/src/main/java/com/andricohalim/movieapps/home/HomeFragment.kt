package com.andricohalim.movieapps.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andricohalim.movieapps.core.data.Resource
import com.andricohalim.movieapps.core.ui.MovieAdapter
import com.andricohalim.movieapps.databinding.FragmentHomeBinding
import com.andricohalim.movieapps.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val detailIntent = Intent(activity, DetailMovieActivity::class.java)
                detailIntent.putExtra(DetailMovieActivity.KEY_DETAIL, selectedData)
                startActivity(detailIntent)
            }

            homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            movieAdapter.setData(movie.data)
                        }

                        is Resource.Error -> {
                            showLoading(false)
                            showError(true)
                        }
                    }
                }
            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isLoading: Boolean) {
        binding.lottieEmpty.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.tvError.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}