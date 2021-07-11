package images_from_url

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

val ShowRemoteImage: @Composable () -> Unit = {
    RemoteImage(url = "abcd", contentDescription = "") {
        Log.i("hasegawa", "error")
    }
}

@Composable
fun RemoteImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    onError: (message: String) -> Unit
) {
    val painter = rememberCoilPainter(url)

    Box {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
        when (val loadState = painter.loadState) {
            is ImageLoadState.Loading -> {
                CircularProgressIndicator()
            }
            is ImageLoadState.Error -> {
                onError(loadState.throwable?.message ?: "Failed to load the image")
            }
        }
    }
}