package zahid.neobrutal.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * A slider component in NeoBrutal style.
 *
 * This slider features a thick rectangular track and a bold square thumb with the
 * characteristic NeoBrutal shadow effect and thick borders.
 *
 * @param value Current value of the slider
 * @param onValueChange Callback invoked when the slider value changes
 * @param modifier Modifier to be applied to the slider
 * @param enabled Whether the slider is enabled
 * @param valueRange The range of values that the slider can take
 * @param steps The number of discrete steps between min and max values
 * @param trackHeight Height of the slider track
 * @param thumbSize Size of the slider thumb
 * @param trackColor Color of the slider track
 * @param activeTrackColor Color of the active part of the track
 * @param thumbColor Color of the slider thumb
 * @param shadowColor Color of the NeoBrutal shadow effect
 * @param shadowOffset How far the shadow is offset from the slider
 * @param borderWidth Width of the borders
 * @param shape Shape of the slider components
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeoSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    trackHeight: Dp = 20.dp,
    thumbSize: Dp = 24.dp,
    trackColor: Color = MaterialTheme.colorScheme.onBackground,
    activeTrackColor: Color = Color(0xFFFF5470),
    thumbColor: Color = Color(0xFF3C40C6),
    shadowColor: Color = MaterialTheme.colorScheme.background,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape
) {
    Box(
        modifier = modifier
            .padding(
                end = shadowOffset,
                bottom = shadowOffset,
                top = thumbSize / 2,
                start = thumbSize / 2
            )
    ) {

        Slider(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            modifier = Modifier
                .fillMaxWidth()
                .height(trackHeight)
                .clip(shape),
            thumb = {
                val interactionSource = remember { MutableInteractionSource() }
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(thumbSize, thumbSize),
                    modifier = Modifier
                        .size(thumbSize)
                        .offset(y = -(shadowOffset / 2))
                        .drawBehind {
                            drawRect(
                                color = shadowColor,
                                size = size,
                                topLeft = Offset(
                                    shadowOffset.toPx(),
                                    shadowOffset.toPx()
                                ),
                            )
                        }
                        .clip(shape)
                        .background(thumbColor)
                        .border(width = borderWidth, color = shadowColor, shape = shape)
                )
            },
            colors = SliderDefaults.colors(
                activeTrackColor = activeTrackColor,
                inactiveTrackColor = trackColor,
                thumbColor = Color.Transparent,
            )
        )
    }
}