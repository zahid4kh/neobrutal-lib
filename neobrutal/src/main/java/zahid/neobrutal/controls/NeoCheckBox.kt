package zahid.neobrutal.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

/**
 * A checkbox component in NeoBrutal style.
 *
 * This checkbox features a simple square with thick borders and sharp corners.
 * It shows a clear visual distinction between checked and unchecked states
 * with bold colors and an optional text label.
 *
 * @param checked Current state of the checkbox
 * @param onCheckedChange Callback invoked when the checkbox state changes
 * @param modifier Modifier to be applied to the checkbox
 * @param text Optional text label to display next to the checkbox
 * @param enabled Whether the checkbox is enabled
 * @param size Size of the checkbox
 * @param checkedColor Background color when the checkbox is checked
 * @param uncheckedColor Background color when the checkbox is unchecked
 * @param checkmarkColor Color of the checkmark icon
 * @param textColor Color of the text label
 * @param shadowColor Color of the NeoBrutal shadow effect
 * @param shadowOffset How far the shadow is offset from the checkbox
 * @param borderWidth Width of the checkbox border
 * @param shape Shape of the checkbox
 */
@Composable
fun NeoCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    size: Dp = 24.dp,
    checkedColor: Color = Color(0xFF3C40C6),
    uncheckedColor: Color = Color.White,
    checkmarkColor: Color = Color.White,
    textColor: Color = Color.Black,
    shadowColor: Color = Color.Black,
    shadowOffset: Dp = 3.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(enabled = enabled) {
            onCheckedChange(!checked)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(end = shadowOffset, bottom = shadowOffset)
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(shadowColor, shape)
            )

            Box(
                modifier = Modifier
                    .size(size)
                    .background(if (checked) checkedColor else uncheckedColor, shape)
                    .border(width = borderWidth, color = shadowColor, shape = shape),
                contentAlignment = Alignment.Center
            ) {
                if (checked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = checkmarkColor,
                        modifier = Modifier.size(size * 0.7f)
                    )
                }
            }
        }
        if (text != null) {
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