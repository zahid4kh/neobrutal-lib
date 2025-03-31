package zahid.neobrutal.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

/**
 * An outlined button in NeoBrutal style.
 *
 * This button features a transparent background with thick borders and the
 * characteristic NeoBrutal shadow offset. It provides a less dominant but still
 * distinctive UI element for secondary actions or in contexts where a filled
 * button would be too visually heavy.
 *
 * @param text The text displayed on the button
 * @param onClick The callback invoked when the button is clicked
 * @param modifier Modifier to be applied to the button
 * @param borderColor The color of the button's border
 * @param textColor The color of the button text
 * @param shadowColor The color of the shadow effect
 * @param backgroundColor Optional background color (transparent by default)
 * @param shadowOffset How far the shadow is offset from the button
 * @param borderWidth Width of the button border
 * @param shape The shape of the button
 * @param enabled Whether the button is enabled
 */
@Composable
fun NeoOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0xFFFF5470),
    textColor: Color = Color(0xFFFF5470),
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = Color.Transparent,
    shadowOffset: Dp = 6.dp,
    borderWidth: Dp = 3.dp,
    shape: Shape = RectangleShape,
    enabled: Boolean = true
) {
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed){
        if (isPressed){
            onClick()
            delay(40)
            isPressed = false
        }

    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = shadowOffset, end = shadowOffset)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = shadowOffset, y = shadowOffset)
                .background(Color.Transparent)
                .border(width = borderWidth, color = shadowColor, shape = shape)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .offset(
                    x = if (isPressed) shadowOffset / 2 else 0.dp,
                    y = if (isPressed) shadowOffset / 2 else 0.dp
                )
                .clickable(enabled = enabled) {
                    isPressed = true
                }
                .background(backgroundColor, shape)
                .border(width = borderWidth, color = borderColor, shape = shape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}