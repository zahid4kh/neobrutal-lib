package zahid.neobrutal.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * A NeoBrutal button with an icon and optional text.
 *
 * This button features a brutalist 3D effect with icons, making it perfect
 * for navigation buttons, action buttons with visual indicators, or anywhere
 * you want to combine icons with the distinct NeoBrutal aesthetic.
 *
 * @param icon The icon to display inside the button
 * @param onClick Callback invoked when the button is clicked
 * @param modifier Modifier to be applied to the button container
 * @param text Optional text to display next to the icon
 * @param iconTint The tint color to apply to the icon
 * @param backgroundColor The background color of the button
 * @param textColor The color for the optional text
 * @param shadowColor The color for the 3D shadow effect
 * @param shadowOffset How far the shadow is offset from the button
 * @param shape The shape of the button
 * @param enabled Whether the button is enabled
 */
@Composable
fun NeoIconButton(icon: ImageVector,
                  onClick: () -> Unit,
                  modifier: Modifier = Modifier,
                  text: String? = null,
                  iconTint: Color = MaterialTheme.colorScheme.onBackground,
                  backgroundColor: Color = MaterialTheme.colorScheme.background,
                  textColor: Color = MaterialTheme.colorScheme.onBackground,
                  shadowColor: Color = MaterialTheme.colorScheme.onBackground,
                  shadowOffset: Dp = 6.dp,
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
                .border(width = 0.dp, color = MaterialTheme.colorScheme.onSurface)
                .background(shadowColor, shape)
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
                .drawBehind {
                    drawRect(
                        color = shadowColor,
                        size = size,
                        style = Stroke(width = 2f)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text ?: "Button icon",
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )

                if (!text.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = text,
                        color = textColor,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}