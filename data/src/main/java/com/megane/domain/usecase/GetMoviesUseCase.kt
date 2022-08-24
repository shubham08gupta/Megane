package com.megane.domain.usecase

import com.megane.Resource
import com.megane.data.repository.MovieRepository
import com.megane.di.IoDispatcher
import com.megane.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Resource<List<Movie>>>(dispatcher) {

    override fun execute(param: Unit): Flow<Resource<List<Movie>>> {
        return movieRepository.getMovies()
    }

}
