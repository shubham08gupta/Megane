package com.oss.megane.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.mavericksViewModel
import com.megane.model.Movie
import com.oss.megane.model.HomeScreenState
import com.oss.megane.ui.theme.MeganeTheme
import com.oss.megane.ui.util.WindowSize
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(windowSize: WindowSize, onMovieClicked: (movieId: String) -> Unit) {
    val viewModel: HomeViewModel = mavericksViewModel()
    val state =
        viewModel.stateFlow.collectAsState(initial = HomeScreenState())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    when (state.value.state) {
        Uninitialized -> {
            /*do nothing*/
        }
        is Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        }
        is Success -> {
            val data = state.value.state.invoke()!!
            when (windowSize) {
                WindowSize.Compact -> MovieList(data, itemsPerRow = 3) { onMovieClicked(it) }
                WindowSize.Medium -> MovieList(data, itemsPerRow = 4) { onMovieClicked(it) }
                WindowSize.Expanded -> MovieList(data, itemsPerRow = 6) { onMovieClicked(it) }
            }
        }
        is Fail -> {
            val exception = (state.value.state as Fail<List<Movie>>).error
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState
                    )
                },
                content = { innerPadding ->
                    Text(
                        text = "Nothing to display",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            )
            scope.launch {
                snackbarHostState.showSnackbar(exception.message ?: "Unknown")
            }
        }
    }

}

@Composable
fun MovieList(
    movies: List<Movie>,
    itemsPerRow: Int,
    modifier: Modifier = Modifier,
    onMovieClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.statusBarsPadding(),
        columns = GridCells.Fixed(itemsPerRow),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = movies) { movie ->
            MovieItem(movie) {
                onMovieClicked(it.id)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClicked: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp)
            .clickable {
                onMovieClicked(movie)
            }
    ) {
        Text(text = movie.title, modifier = Modifier.padding(8.dp))
    }
}

@Preview(name = "Phone", showBackground = true, showSystemUi = true)
@Preview(showBackground = true, device = Devices.NEXUS_7_2013)
@Preview(
    name = "Medium tablet (dark)",
    showBackground = true,
    device = Devices.NEXUS_7_2013,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(name = "Expanded tablet", showBackground = true, device = Devices.PIXEL_C)
@Preview(
    name = "Phone (dark)",
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true,
    fontScale = 1.5f
)
@Composable
fun HomeScreenPreview() {
    MeganeTheme {
        HomeScreen(WindowSize.Medium) { }
    }
}
