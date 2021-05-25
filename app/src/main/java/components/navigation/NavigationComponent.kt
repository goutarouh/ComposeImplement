package components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.composeimplement.MainView
import com.github.composeimplement.ui.theme.ComposeImplementTheme
import components.todo.AddView
import components.todo.ItemList

@Composable
fun NavigationComponent() {

    val navController = rememberNavController()

    ComposeImplementTheme {

        CompositionLocalProvider(LocalNavigation provides MainNavigator(navController)) {

            NavHost(navController = navController, startDestination = Destinations.Main.path) {
                composable(Destinations.Main.path) { MainView() }
                composable(Destinations.TodoList.path) { ItemList() }
                composable(Destinations.AddTodo.path) { AddView() }
            }
        }
    }
}