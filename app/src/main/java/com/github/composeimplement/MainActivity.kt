package com.github.composeimplement

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import components.navigation.Destinations
import components.navigation.NavigationComponent
import database.TodoItem
import database.TodoViewModel
import database.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponent()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView(navController: NavController) {
    val destList = Destinations.values().filter { it.startScreen }

    LazyColumn {
        items(destList) { dest ->
            ListItem(
                text = { Text(text = dest.path) },
                modifier = Modifier.clickable {
                    navController.navigate(dest.path)
                }
            )
            Divider()
        }
    }
}
