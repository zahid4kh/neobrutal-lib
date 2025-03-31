package zahid.neobrutal.buttons

import androidx.compose.foundation.background
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
    activeColor: Color = Color(0xFF3C40C6),
    inactiveColor: Color = Color(0xFFA71A25),
    activeTextColor: Color = Color(0xFFFFFFFF),
    inactiveTextColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 8.dp,
    shape: Shape = RectangleShape,
    enabled: Boolean = true
) {
    // might add to drawBehind
    // val transparentColor = Color.Transparent

    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        if (isActive) {

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(shadowColor, shape)
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .offset(x = shadowOffset / 2, y = shadowOffset / 2)
                    .clickable(enabled = enabled) {
                        onToggle(!isActive)
                    }
                    .background(activeColor, shape)
                    .drawBehind {
                        drawRect(
                            color = shadowColor,
                            size = size,
                            style = Stroke(width = 5f)
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
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = shadowOffset/2, y = shadowOffset/2)
                    .background(shadowColor, shape)
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(enabled = enabled) {
                        onToggle(!isActive)
                    }
                    .background(inactiveColor, shape)
                    .drawBehind {
                        drawRect(
                            color = shadowColor,
                            size = size,
                            style = Stroke(width = 5f)
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
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        }
    }
}