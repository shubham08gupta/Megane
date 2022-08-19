package com.megane.data.repository

import com.megane.Resource
import com.megane.data.local.moviesList
import com.megane.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val NetworkDelay = 2000L

interface MovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
}

class MovieRepositoryImpl : MovieRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading)
        delay(NetworkDelay)
        emit(Resource.Success(moviesList))
    }

}
