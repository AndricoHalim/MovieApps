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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                false // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

//    companion object {
//        @Volatile
//        private var instance: MovieRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): MovieRepository =
//            instance ?: synchronized(this) {
//                instance ?: MovieRepository(remoteData, localData, appExecutors)
//            }
//    }
}
