package com.oss.megane

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.oss.megane.navigation.BottomNavigationBar
import com.oss.megane.navigation.RootNavigationGraph
import com.oss.megane.ui.theme.MeganeTheme
import com.oss.megane.ui.util.WindowSize
import com.oss.megane.ui.util.rememberWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        Log.e("Shubham", "${Resources.getSystem().configuration.locales}")
        Log.e("Shubham", Resources.getSystem().configuration.locales[0].script)
//        Locale.getAvailableLocales().map {
//            Log.e("Shubham", "${it.country} ${it.displayCountry} ")
//        }

        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            MainScreenView(windowSizeClass)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreenView(WindowSize.Medium)
}

@Composable
fun MainScreenView(
    windowSize: WindowSize,
) {
    MeganeTheme {
        val systemUiController = rememberSystemUiController()
        val darkIcons = isSystemInDarkTheme()
        SideEffect {
            // set navigation bar and system bar color
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = !darkIcons)
        }

        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    modifier = Modifier.navigationBarsPadding(),
                    navController = navController
                )
            }
        ) { innerPadding ->
            RootNavigationGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                windowSize = windowSize
            )
        }
    }
}
