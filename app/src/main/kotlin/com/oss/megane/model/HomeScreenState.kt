package com.oss.megane.model

import com.airbnb.mvrx.MavericksState
import com.megane.model.Movie

/**
 * State of the screen as represented by the [state] variable. Every screen will be considered
 * as a [state]
 */
data class HomeScreenState(val state: HomeScreenUI = HomeScreenUI.Loading) : MavericksState

/**
 * Possible states of the [com.oss.megane.ui.home.HomeScreen]
 */
sealed class HomeScreenUI {
    object Loading : HomeScreenUI()
    data class Success(val moviesList: List<Movie>) : HomeScreenUI()
    data class Error(val exception: Exception) : HomeScreenUI()
}
