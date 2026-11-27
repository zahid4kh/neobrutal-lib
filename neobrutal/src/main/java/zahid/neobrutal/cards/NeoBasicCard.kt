package zahid.neobrutal.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zahid.neobrutal.shared.NeoCardSurface

/**
 * A basic card component in NeoBrutal style.
 *
 * This card features a title, optional subtitle, and content area with the
 * characteristic NeoBrutal shadow offset and thick borders.
 *
 * @param title Composable slot drawn at the top of the card
 * @param onClick Callback invoked when the card is clicked
 * @param modifier Modifier to be applied to the card
 * @param subtitle Optional subtitle text
 * @param backgroundColor The background color of the card
 * @param subtitleColor The color of the subtitle text
 * @param shadowColor The color of the shadow effect
 * @param shadowOffset How far the shadow is offset from the card
 * @param borderWidth The width of the card border
 * @param shape The shape of the card
 * @param content The composable content to display in the card body
 */
@Composable
fun NeoBasicCard(
    title: @Composable () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    subtitleColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 8.dp,
    borderWidth: Dp = 2.2.dp,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit,
) {
    NeoCardSurface(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shadowColor = shadowColor,
        shadowOffset = shadowOffset,
        borderWidth = borderWidth,
        shape = shape,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {
            title()

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = subtitleColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            content()
        }
    }
}
