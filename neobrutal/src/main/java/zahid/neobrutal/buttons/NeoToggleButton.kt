package zahid.neobrutal.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zahid.neobrutal.shared.NeoButtonSurface

/**
 * A toggle button in NeoBrutal style.
 *
 * This button can be toggled between on and off states with distinct visual appearances.
 * When active, the face stays shifted into the shadow. When inactive, the face sits
 * raised above the shadow.
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
    activeColor: Color = Color(0xFF249A02),
    inactiveColor: Color = Color(0xFFA71A25),
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 4.dp,
    shape: Shape = RectangleShape,
    enabled: Boolean = true
) {
    NeoButtonSurface(
        modifier = modifier,
        backgroundColor = if (isActive) activeColor else inactiveColor,
        shadowColor = shadowColor,
        shadowOffset = shadowOffset,
        shape = shape,
        pressed = isActive,
        enabled = enabled,
        onClick = { onToggle(!isActive) }
    ) {
        Text(
            text = text,
            color = if (isActive) activeTextColor else inactiveTextColor,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}
