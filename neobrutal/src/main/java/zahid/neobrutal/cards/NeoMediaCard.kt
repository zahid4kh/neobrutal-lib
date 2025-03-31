package zahid.neobrutal.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A media card component in NeoBrutal style.
 *
 * This card is designed for displaying media content with an image/thumbnail,
 * title, description, and optional action buttons. It features the characteristic
 * NeoBrutal shadow offset and bold borders.
 *
 * @param image The image to display in the card
 * @param title The title text for the media
 * @param description Brief description or caption for the media
 * @param modifier Modifier to be applied to the card
 * @param actions Optional composable for action buttons
 * @param backgroundColor The background color of the card
 * @param titleColor The color of the title text
 * @param descriptionColor The color of the description text
 * @param shadowColor The color of the shadow effect
 * @param shadowOffset How far the shadow is offset from the card
 * @param borderWidth The width of the card border
 * @param shape The shape of the card
 * @param imageShape The shape to apply to the image (defaults to same as card)
 */
@Composable
fun NeoMediaCard(
    image: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    actions: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    descriptionColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 8.dp,
    borderWidth: Dp = 2.5.dp,
    shape: Shape = RectangleShape,
    imageShape: Shape = shape
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
                .clip(imageShape)
                .offset(x = 0.dp, y = 0.dp)
                .background(backgroundColor, shape)
                .border(width = borderWidth, color = shadowColor, shape = shape)
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(borderWidth)
                        .clip(imageShape)
                    //.border(width = borderWidth, color = shadowColor, shape = imageShape)
                ) {
                    Image(
                        painter = image,
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.wrapContentSize()
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        color = titleColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = description,
                        color = descriptionColor,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = if (actions != null) 16.dp else 0.dp)
                    )

                    if (actions != null) {
                        actions()
                    }
                }
            }
        }
    }
}