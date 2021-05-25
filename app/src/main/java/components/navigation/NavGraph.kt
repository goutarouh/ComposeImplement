package components.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

enum class Destinations(
    /** 画面説明 */
    val description: String,
    /** 画面のpathをユニークに設定する。 */
    val path: String,
    /** 起動時画面に並べるかどうか */
    val startScreen: Boolean
) {

    Main("アプリ起動時の画面", "main", false),

    // Todoアプリ
    TodoList("Todo一覧", "todoList", true),
    AddTodo("Todoを追加", "addTodo", false),

}


val LocalNavigation = compositionLocalOf<Navigator> {
    error("Navigator not found")
}