package com.oss.megane.ui.home

import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.megane.Resource
import com.megane.domain.usecase.GetMoviesUseCase
import com.oss.megane.model.HomeScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeScreenState,
    private val getMoviesUseCase: GetMoviesUseCase,
) : MavericksViewModel<HomeScreenState>(initialState) {

    init {
        viewModelScope.launch {
            getMoviesUseCase(Unit).collect { resource ->
                when (resource) {
                    Resource.Loading -> {
                        setState { copy(state = Loading()) }
                    }
                    is Resource.Success -> {
                        setState { copy(state = Success(resource.data)) }
                    }
                     is Resource.Error -> {
                        setState { copy(state = Fail(resource.exception)) }
                    }
                }
            }
        }

    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeScreenState> {
        override fun create(state: HomeScreenState): HomeViewModel
    }

    companion object :
        MavericksViewModelFactory<HomeViewModel, HomeScreenState> by hiltMavericksViewModelFactory()

}
