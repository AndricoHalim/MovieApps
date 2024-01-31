package com.andricohalim.movieapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.movieapps.BuildConfig.IMAGE_URL
import com.andricohalim.movieapps.core.domain.model.Movie
import com.andricohalim.movieapps.core.ui.ViewModelFactory
import com.andricohalim.movieapps.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailTourismViewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailTourismViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val detailTourism = intent.getParcelableExtra<Movie>(KEY_DETAIL)
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailTourism: Movie?) {
        detailTourism?.let {
            supportActionBar?.title = detailTourism.title
            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_URL + detailTourism.backdropPath)
                .into(binding.ivBackgroundMove)
            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_URL + detailTourism.posterPath)
                .into(binding.ivMovie)
            binding.tvMovieName.text = detailTourism.title

//            var statusFavorite = detailTourism.isFavorite
//            setStatusFavorite(statusFavorite)
//            binding.fab.setOnClickListener {
//                statusFavorite = !statusFavorite
//                detailTourismViewModel.setFavoriteTourism(detailTourism, statusFavorite)
//                setStatusFavorite(statusFavorite)
//            }
        }
    }

//    private fun setStatusFavorite(statusFavorite: Boolean) {
//        if (statusFavorite) {
//            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
//        } else {
//            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
//        }
//    }

    companion object {
        const val KEY_DETAIL = "key_detail"
    }
}