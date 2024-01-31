package com.andricohalim.movieapps.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.andricohalim.movieapps.core.data.source.local.LocalDataSource
import com.andricohalim.movieapps.core.data.source.remote.RemoteDataSource
import com.andricohalim.movieapps.core.data.source.remote.network.ApiResponse
import com.andricohalim.movieapps.core.data.source.remote.response.MovieResponse
import com.andricohalim.movieapps.core.domain.model.Movie
import com.andricohalim.movieapps.core.domain.repository.IMovieRepository
import com.andricohalim.movieapps.core.utils.AppExecutors
import com.andricohalim.movieapps.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}
