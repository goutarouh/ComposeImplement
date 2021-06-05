package components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.github.composeimplement.MainView
import components.todo.AddView
import components.todo.ItemListView
import playground.PlaygroundView

enum class Destinations(
    /** 画面説明 */
    val description: String,
    /** 画面のpathをユニークに設定する。 */
    val path: String,
    /** 起動時画面に並べるかどうか */
    val startScreen: Boolean,
    /** 起動時のComposable関数 */
    val composable: @Composable () -> Unit
) {

    Main("アプリ起動時の画面", "main", false, MainView),

    // Todoアプリ
    TodoList("Todo一覧", "todoList", true, ItemListView),
    AddTodo("Todoを追加", "addTodo", false, AddView),

    // 練習用
    Playground("練習", "playground", true, PlaygroundView),

}


val LocalNavigation = compositionLocalOf<Navigator> {
    error("Navigator not found")
}