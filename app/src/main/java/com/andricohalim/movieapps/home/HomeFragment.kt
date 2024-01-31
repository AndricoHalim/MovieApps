package com.andricohalim.movieapps.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andricohalim.movieapps.core.data.Resource
import com.andricohalim.movieapps.core.ui.MovieAdapter
import com.andricohalim.movieapps.core.ui.ViewModelFactory
import com.andricohalim.movieapps.databinding.FragmentHomeBinding
import com.andricohalim.movieapps.detail.DetailMovieActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
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

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}