package components.todo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import components.navigation.Destinations
import components.navigation.LocalNavigation
import database.TodoItem
import database.TodoViewModel
import database.TodoViewModelFactory

val AddView: @Composable () -> Unit =  {
    val inputViewModel = InputViewModel()
    val context = LocalContext.current
    val navigator = LocalNavigation.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(
        floatingActionButton = {
            ExtendedFAB {
                insertTodoInDB(inputViewModel.todo.value.toString(), mTodoViewModel)

                Toast.makeText(context, "Added Todo", Toast.LENGTH_SHORT).show()
                navigator.navigateTo(Destinations.TodoList.path)
            }
        }
    ) {
        InputFieldState(inputViewModel)
    }
}

@Composable
fun InputFieldState(inputViewModel: InputViewModel) {
    val todo: String by inputViewModel.todo.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        InputField(name = todo) { inputViewModel.onInputChange(it) }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun InputField(
    name: String,
    onValueChange: (String) -> Unit
) {
   val focusManager = LocalFocusManager.current

    TextField(
        value = name,
        placeholder = { Text(text = "Enter todo") },
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
        onValueChange = onValueChange,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()})
    )

}

@Composable
fun ExtendedFAB(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(text = "Save Todo") },
        onClick = onClick
    )
}

@Preview
@Composable
fun PreviewInputField() {
    InputField(name = "hello") {
        Log.i("hasegawa", "clicked")
    }
}

fun insertTodoInDB(todo: String, mTodoViewModel: TodoViewModel) {
    if (todo.isNotEmpty()) {
        val todoItem = TodoItem(
            itemName = todo,
            isDone = false
        )
        mTodoViewModel.addTodo(todoItem)
    }
}


class InputViewModel: ViewModel() {
    private val _todo: MutableLiveData<String> = MutableLiveData("")
    val todo: LiveData<String> = _todo

    fun onInputChange(newName: String) {
        _todo.value = newName
    }
}