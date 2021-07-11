package components.navigation

import androidx.navigation.NavController


interface Navigator {
    fun navigateBack()
    fun navigateTo(path: String)
}

class MainNavigator(private val
                    navController: NavController): Navigator {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateTo(path: String) {
        navController.navigate(path)
    }
}