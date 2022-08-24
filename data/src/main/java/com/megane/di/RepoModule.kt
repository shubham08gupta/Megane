package com.megane.di

import com.megane.data.repository.MovieRepository
import com.megane.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindMovieRepo(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
