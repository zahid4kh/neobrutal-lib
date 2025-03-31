package zahid.neobrutal.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A basic card component in NeoBrutal style.
 *
 * This card features a title, optional subtitle, and content area with the
 * characteristic NeoBrutal shadow offset and thick borders.
 *
 * @param title The title text for the card
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
    borderWidth: Dp = 0.dp,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = shadowOffset, end = shadowOffset)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = shadowOffset, y = shadowOffset)
                .background(shadowColor, shape)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .clickable{
                    onClick()
                }
                .offset(x = 0.dp, y = 0.dp)
                .background(backgroundColor, shape)
                .border(width = borderWidth, color = shadowColor, shape = shape)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
}