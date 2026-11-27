package zahid.neobrutal.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import zahid.neobrutal.shared.NeoButtonSurface

/**
 * A button component in NeoBrutal style.
 *
 * The NeoBrutal style emphasizes bold geometric shapes with strong shadows and
 * thick borders. This button has a 3D effect that changes on press.
 *
 * @param modifier Modifier to be applied to the button.
 * @param text The text displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param enabled Controls whether the button can be clicked. When `false`, clicks are ignored. The colors stay the same.
 * @param backgroundColor The background color of the button.
 * @param textColor The color of the button text.
 * @param shadowColor The color of the 3D shadow effect.
 * @param shadowOffset How far the shadow is offset from the button.
 * @param shape The shape of the button (defaults to rectangular).
 */
@Composable
fun NeoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 6.dp,
    shape: Shape = RectangleShape
) {
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(40)
            isPressed = false
        }
    }

    NeoButtonSurface(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shadowColor = shadowColor,
        shadowOffset = shadowOffset,
        shape = shape,
        pressed = isPressed,
        enabled = enabled,
        onClick = {
            isPressed = true
            onClick()
        }
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}
