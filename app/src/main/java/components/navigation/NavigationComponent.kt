package components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.composeimplement.ui.theme.ComposeImplementTheme


@Composable
fun NavigationComponent() {

    val navController = rememberNavController()

    ComposeImplementTheme {

        CompositionLocalProvider(LocalNavigation provides MainNavigator(navController)) {

            NavHost(navController = navController, startDestination = Destinations.Main.path) {

                Destinations.values().forEach { dest ->
                    composable(dest.path) { dest.composable() }
                }

            }
        }
    }
}