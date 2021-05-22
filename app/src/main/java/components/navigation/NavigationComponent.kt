package components.navigation

import androidx.compose.runtime.Composable
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

        NavHost(navController = navController, startDestination = Destinations.Main.path) {
            composable(Destinations.Main.path) { MainView(navController) }
            composable(Destinations.TodoList.path) { ItemList(navController) }
            composable(Destinations.AddTodo.path) { AddView(navController) }
        }
    }
}