package zahid.neobrutal.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * A button component in NeoBrutal style.
 *
 * The NeoBrutal style emphasizes bold geometric shapes with strong shadows and
 * thick borders. This button has a 3D effect that changes on press.
 *
 * @param text The text displayed on the button
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier Modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param backgroundColor The background color of the button
 * @param textColor The color of the button text
 * @param shadowColor The color of the 3D shadow effect
 * @param shape The shape of the button (defaults to rectangular)
 */
@Composable
fun NeoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Color(0xFFFF5470),
    textColor: Color = Color.White,
    shadowColor: Color = Color.Black,
    shape: Shape = RectangleShape
) {
    // Track pressed state to show button press animation
    var isPressed by remember { mutableStateOf(false) }

    // Main button container
    Box(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = {
                    onClick()
                }
            )
            .padding(bottom = 8.dp, end = 8.dp)
    ) {
        // Shadow/backdrop layer
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 8.dp, y = 8.dp)
                .background(shadowColor, shape)
        )

        // Button surface
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(
                    x = if (isPressed) 4.dp else 0.dp,
                    y = if (isPressed) 4.dp else 0.dp
                )
                .background(backgroundColor, shape)
                .drawBehind {
                    // Draw border
                    drawRect(
                        color = shadowColor,
                        size = size,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = 2f
                        )
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            // Button text
            Text(
                text = text,
                color = textColor,
                style = TextStyle(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}