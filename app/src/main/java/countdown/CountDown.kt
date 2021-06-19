package countdown.ui

import android.view.View
import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.github.composeimplement.R


val CountDownALl: @Composable () -> Unit = {



    MyCountDownApp()
}

@Composable
fun MyCountDownApp() {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.maroon))
            .padding(16.dp)
            .fillMaxWidth(),
    ) {

        CountDownScreen()
    }
}

