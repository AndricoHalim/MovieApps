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
import com.andricohalim.movieapps.core.ui.ViewModelFactory
import com.andricohalim.movieapps.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val detailViewModel: DetailMovieViewModel by viewModels {
        factory
    }
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val factory = ViewModelFactory.getInstance(this)
//        detailViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val detailMovie = intent.getParcelableExtra<Movie>(KEY_DETAIL)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_URL + detailMovie.backdropPath)
                .into(binding.ivBackgroundMove)
            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_URL + detailMovie.posterPath)
                .into(binding.ivMovie)
            binding.tvMovieName.text = detailMovie.title
            binding.tvMovieRelease.text= detailMovie.releaseDate
            binding.tvMovieOverview.text = detailMovie.overview

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
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