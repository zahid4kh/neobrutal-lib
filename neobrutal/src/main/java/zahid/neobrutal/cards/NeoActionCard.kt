package zahid.neobrutal.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * An action card component in NeoBrutal style.
 *
 * This card is designed for dashboard widgets or quick action panels with an icon,
 * title, brief description, and prominent action buttons. It features the
 * characteristic NeoBrutal shadow offset and bold borders.
 *
 * @param icon The icon to display in the card
 * @param title The title text for the card
 * @param description Brief description of the card's purpose
 * @param modifier Modifier to be applied to the card
 * @param iconSize The size of the icon (default is 40.dp)
 * @param backgroundColor The background color of the card
 * @param iconTint The tint color for the icon
 * @param accentColor Accent color for visual emphasis
 * @param titleColor The color of the title text
 * @param descriptionColor The color of the description text
 * @param shadowColor The color of the shadow effect
 * @param shadowOffset How far the shadow is offset from the card
 * @param borderWidth The width of the card border
 * @param shape The shape of the card
 * @param actions Composable for action buttons
 */
@Composable
fun NeoActionCard(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    iconTint: Color = Color(0xFFD5D18D),
    accentColor: Color = Color(0xFF3C40C6),
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    descriptionColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 8.dp,
    borderWidth: Dp = 2.2.dp,
    shape: Shape = RectangleShape,
    actions: @Composable () -> Unit,
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
                .offset(x = 0.dp, y = 0.dp)
                .background(backgroundColor, shape)
                .border(width = borderWidth, color = shadowColor, shape = shape)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(iconSize)
                            .background(accentColor)
                            .border(width = borderWidth, color = shadowColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = iconTint,
                            modifier = Modifier.size(iconSize * 0.6f)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = title,
                        color = titleColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = description,
                    color = descriptionColor,
                    fontSize = 14.sp,
                )

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    actions()
                }
            }
        }
    }
}