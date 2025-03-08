package zahid.neobrutal.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A toggle button in NeoBrutal style.
 *
 * This button can be toggled between on and off states with distinct visual appearances.
 * When active, it appears with a bold background color and looks "pressed in". When
 * inactive, it has a raised appearance. The characteristic NeoBrutal shadow offset is
 * present in both states but changes position to enhance the visual state difference.
 *
 * @param text The text displayed on the button
 * @param isActive Current toggle state of the button
 * @param onToggle Callback invoked when the toggle state changes
 * @param modifier Modifier to be applied to the button
 * @param width The width of the button (default is 160.dp)
 * @param height The height of the button (default is 48.dp)
 * @param activeColor Background color when the button is in active state
 * @param inactiveColor Background color when the button is in inactive state
 * @param activeTextColor Text color when the button is in active state
 * @param inactiveTextColor Text color when the button is in inactive state
 * @param shadowColor Color for the 3D shadow effect
 * @param shadowOffset How far the shadow is offset from the button
 * @param shape The shape of the button
 * @param enabled Whether the button is enabled
 */
@Composable
fun NeoToggleButton(
    text: String,
    isActive: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 165.dp,
    height: Dp = 48.dp,
    activeColor: Color = Color(0xFF3C40C6),
    inactiveColor: Color = Color(0xFFE9EAEC),
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Black,
    shadowColor: Color = Color.Black,
    shadowOffset: Dp = 8.dp,
    shape: Shape = RectangleShape,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        if (isActive) {

            Box(
                modifier = Modifier
                    .width(width - shadowOffset)
                    .height(height - shadowOffset)
                    .background(shadowColor, shape)
            )
            Box(
                modifier = Modifier
                    .width(width - shadowOffset)
                    .height(height - shadowOffset)
                    .offset(x = shadowOffset / 2, y = shadowOffset / 2)
                    .clickable(enabled = enabled) {
                        onToggle(!isActive)
                    }
                    .background(activeColor, shape)
                    .drawBehind {
                        drawRect(
                            color = shadowColor,
                            size = size,
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    color = activeTextColor,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .width(width - shadowOffset)
                    .height(height - shadowOffset)
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(shadowColor, shape)
            )
            Box(
                modifier = Modifier
                    .width(width - shadowOffset)
                    .height(height - shadowOffset)
                    .clickable(enabled = enabled) {
                        onToggle(!isActive)
                    }
                    .background(inactiveColor, shape)
                    .drawBehind {
                        drawRect(
                            color = shadowColor,
                            size = size,
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    color = inactiveTextColor,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}