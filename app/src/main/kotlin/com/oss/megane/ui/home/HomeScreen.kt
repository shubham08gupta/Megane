package com.oss.megane.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oss.megane.ui.theme.MeganeTheme
import com.oss.megane.ui.util.WindowSize

@Composable
fun HomeScreen(windowSize: WindowSize) {
    when (windowSize) {
        WindowSize.Compact -> MovieList(itemsPerRow = 3)
        WindowSize.Medium -> MovieList(itemsPerRow = 4)
        WindowSize.Expanded -> MovieList(itemsPerRow = 6)
    }
}

@Composable
fun MovieList(modifier: Modifier = Modifier, itemsPerRow: Int) {
    LazyVerticalGrid(
        modifier = modifier.statusBarsPadding(),
        columns = GridCells.Fixed(itemsPerRow),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(count = 6) {
            MovieItem()
        }
    }
}

@Composable
fun MovieItem() {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp)
    ) {
        Text(text = "Don't Look Up")
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
        HomeScreen(WindowSize.Medium)
    }
}