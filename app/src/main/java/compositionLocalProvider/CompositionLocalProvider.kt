package compositionLocalProvider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MyCompositionLocalProvider(navController: NavController) {
    CompositionLocalProvider(LocalUser provides CompositionData("gouta", 100)) {
        MyContent()
    }
}

@Composable
fun MyContent() {
    val user = LocalUser.current
    Text(
        text = user.name,
        modifier = Modifier.fillMaxWidth(),
    )
}

val LocalUser = compositionLocalOf {
    CompositionData()
}


data class CompositionData(
    val name: String = "",
    val age: Int = 0
)