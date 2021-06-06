package countdown.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

@Composable
fun ShowCelebrations() {
    AndroidView(
        modifier = Modifier
            .size(200.dp, 200.dp)
            .padding(top = 10.dp),
        factory = {
                  KonfettiView(context = it).apply {

                  }
        },
        update = {
            it.build()
                .addColors(
                    android.graphics.Color.YELLOW,
                    android.graphics.Color.GREEN,
                    android.graphics.Color.MAGENTA
                )
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square)
                .addSizes(Size(12))
                .setPosition(-50f, -50f, -50f)
                .streamFor(300, 5000L)
        }
    )
}