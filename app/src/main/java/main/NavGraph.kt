package main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import components.navigation.Navigator
import components.todo.ItemListView
import countdown.ui.CountDownALl
import images_from_url.ShowRemoteImage
import playground.PlaygroundView
import todo.AddView

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

    // カウントダウン
    CountDonw("CountDown", "countdown", true, CountDownALl),

    // Images from remote url.
    GetRemoteImage("URLで画像取得&表示", "imagesFromUrl", true, ShowRemoteImage),

    // 練習用
    Playground("練習", "playground", true, PlaygroundView),

}


val LocalNavigation = compositionLocalOf<Navigator> {
    error("Navigator not found")
}