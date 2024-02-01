package com.andricohalim.movieapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.movieapps.BuildConfig.IMAGE_URL
import com.andricohalim.movieapps.MyApplication
import com.andricohalim.movieapps.R
import com.andricohalim.movieapps.core.domain.model.Movie
import com.andricohalim.movieapps.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val detailViewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(KEY_DETAIL)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.apply {
                Glide.with(this@DetailMovieActivity)
                    .load(IMAGE_URL + detailMovie.backdropPath)
                    .into(ivBackgroundMove)
                Glide.with(this@DetailMovieActivity)
                    .load(IMAGE_URL + detailMovie.posterPath)
                    .into(ivMovie)
                tvMovieName.text = detailMovie.title
                tvMovieRelease.text= detailMovie.releaseDate
                tvMovieOverview.text = detailMovie.overview

                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)
                btnFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_added_24))
        } else {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_add_24))
        }
    }

    companion object {
        const val KEY_DETAIL = "key_detail"
    }
}