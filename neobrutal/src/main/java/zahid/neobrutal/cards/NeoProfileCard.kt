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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
 * A profile card component in NeoBrutal style.
 *
 * This card is designed for displaying user profiles with an avatar image,
 * name, role/title, and additional information or stats. It features the
 * characteristic NeoBrutal shadow offset and bold borders.
 *
 * @param modifier Modifier to be applied to the card
 * @param avatar The profile picture/avatar image
 * @param name The name of the profile
 * @param title The role or title of the profile
 * @param stats Optional composable for displaying profile statistics
 * @param actions Optional composable for action buttons
 * @param avatarSize The size of the avatar image (default is 80.dp)
 * @param backgroundColor The background color of the card
 * @param nameColor The color of the name text
 * @param titleColor The color of the title/role text
 * @param shadowColor The color of the shadow effect
 * @param shadowOffset How far the shadow is offset from the card
 * @param borderWidth The width of the card border
 * @param cardShape The shape of the card
 */
@Composable
fun NeoProfileCard(
    modifier: Modifier = Modifier,
    avatar: Painter,
    name: String,
    title: String,
    stats: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    avatarSize: Dp = 80.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    nameColor: Color = MaterialTheme.colorScheme.onBackground,
    titleColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 8.dp,
    borderWidth: Dp = 2.2.dp,
    cardShape: Shape = RectangleShape
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
                .background(shadowColor, cardShape)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .offset(x = 0.dp, y = 0.dp)
                .background(backgroundColor, cardShape)
                .border(width = borderWidth, color = shadowColor, shape = cardShape)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .size(avatarSize)
                        .clip(CircleShape)
                        .border(width = borderWidth, color = shadowColor, shape = CircleShape)
                ) {
                    Image(
                        painter = avatar,
                        contentDescription = "Profile picture of $name",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.wrapContentSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = name,
                    color = nameColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )
                if (stats != null) {
                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 16.dp),
                        thickness = 2.dp,
                        color = shadowColor
                    )

                    stats()
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (actions != null) {
                    if (stats == null) {
                        HorizontalDivider(
                            modifier = Modifier.padding(bottom = 16.dp),
                            thickness = 2.dp,
                            color = shadowColor
                        )
                    }
                    actions()
                }
            }
        }
    }
}