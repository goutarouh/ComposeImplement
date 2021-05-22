package components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.composeimplement.HomeView
import com.github.composeimplement.ui.theme.ComposeImplementTheme
import components.addTodo.AddView

@Composable
fun NavigationComponent() {

    val navController = rememberNavController()

    ComposeImplementTheme {

        NavHost(navController = navController, startDestination = Destinations.Home) {
            composable(Destinations.Home) { HomeView(navController) }
            composable(Destinations.AddTodo) { AddView(navController) }
        }
    }
}