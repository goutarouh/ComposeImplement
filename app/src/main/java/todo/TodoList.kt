package components.todo

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import todo.TodoItem
import database.TodoViewModel
import database.TodoViewModelFactory
import main.Destinations
import main.LocalNavigation


val ItemListView: @Composable () -> Unit = {

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    val items = mTodoViewModel.readAllData.observeAsState(listOf()).value


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "My ToDo List")
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        CustomeCardState(mTodoViewModel = mTodoViewModel)
        TodoList(list = items, mTodoViewModel = mTodoViewModel)
        Spacer(modifier = Modifier.padding(top = 32.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoList(
    list: List<TodoItem>,
    mTodoViewModel: TodoViewModel
) {
    val context = LocalContext.current

    LazyColumn {
        items(list) { todo ->
            val name = rememberSaveable { mutableStateOf(todo.isDone) }

            ListItem(
                text = { Text(text = todo.itemName ) },
                icon = {
                    IconButton(onClick = {
                        mTodoViewModel.deleteTodo(todo)
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                },
                trailing = {
                    Checkbox(
                        checked = name.value,
                        onCheckedChange = {
                            name.value = it
                            todo.isDone = name.value
                            mTodoViewModel.updateTodo(todo)

                            Toast.makeText(context, "Updated todo!", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            )
            Divider()
        }
    }
}

@Composable
fun CustomeCardState(
    mTodoViewModel: TodoViewModel
) {
    val navigator = LocalNavigation.current
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navigator.navigateTo(Destinations.AddTodo.path) }) {
                Text(text = "Add Todo")
            }
            Button(onClick = { mTodoViewModel.deleteAllTodos() }) {
                Text(text = "Clear all")
            }
        }
    }
}