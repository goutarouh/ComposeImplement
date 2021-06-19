package main

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.navigation.NavigationComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponent()
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
val MainView: @Composable () -> Unit =  {

    val navigator = LocalNavigation.current

    val destList = Destinations.values().filter { it.startScreen }

    LazyColumn {
        items(destList) { dest ->
            ListItem(
                text = { Text(text = dest.path) },
                modifier = Modifier.clickable {
                    navigator.navigateTo(dest.path)
                }
            )
            Divider()
        }
    }
}
