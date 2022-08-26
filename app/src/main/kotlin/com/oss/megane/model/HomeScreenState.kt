package com.oss.megane.model

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.megane.model.Movie

/**
 * State of the screen as represented by the [state] variable. Every screen will be considered
 * as a [state]
 */
data class HomeScreenState(val state: Async<List<Movie>> = Uninitialized) : MavericksState
