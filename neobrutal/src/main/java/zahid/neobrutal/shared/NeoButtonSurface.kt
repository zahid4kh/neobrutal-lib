package zahid.neobrutal.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun NeoSurface(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shadowColor: Color,
    shadowOffset: Dp = 6.dp,
    borderWidth: Dp = 2.dp,
    borderColor: Color = shadowColor,
    shape: Shape = RectangleShape,
    pressed: Boolean = false,
    outlinedShadow: Boolean = false,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
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
                .then(
                    if (outlinedShadow) {
                        Modifier
                            .background(Color.Transparent, shape)
                            .border(width = borderWidth, color = shadowColor, shape = shape)
                    } else {
                        Modifier.background(shadowColor, shape)
                    }
                )
        )
        Box(
            modifier = Modifier
                .wrapContentSize()
                .offset(
                    x = if (pressed) shadowOffset / 2 else 0.dp,
                    y = if (pressed) shadowOffset / 2 else 0.dp
                )
                .then(
                    if (onClick != null) {
                        Modifier.clickable(enabled = enabled, onClick = onClick)
                    } else {
                        Modifier
                    }
                )
                .background(backgroundColor, shape)
                .border(width = borderWidth, color = borderColor, shape = shape),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}
