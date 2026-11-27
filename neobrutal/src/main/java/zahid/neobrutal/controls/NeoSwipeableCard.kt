package zahid.neobrutal.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import zahid.neobrutal.R
import zahid.neobrutal.shared.NeoControlSurface
import kotlin.math.roundToInt

enum class DragState { NotSwiped, Swiped }

@Composable
fun NeoSwipeableCard(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    deleteButtonColor: Color = Color.Red,
    deleteIconColor: Color = Color.White,
    shadowOffset: Dp = 6.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    val deleteButtonWidth = 80.dp
    val deleteButtonWidthPx = with(density) { deleteButtonWidth.toPx() }

    var isDeleteVisible by remember { mutableStateOf(false) }

    val draggableState = remember {
        AnchoredDraggableState<DragState>(
            initialValue = DragState.NotSwiped,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 125.dp.toPx() } },
            snapAnimationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            decayAnimationSpec = exponentialDecay(),
            confirmValueChange = { true }
        )
    }

    LaunchedEffect(deleteButtonWidthPx) {
        draggableState.updateAnchors(
            DraggableAnchors {
                DragState.NotSwiped at 0f
                DragState.Swiped at -deleteButtonWidthPx
            }
        )
    }

    LaunchedEffect(Unit) {
        snapshotFlow {
            try {
                draggableState.requireOffset()
            } catch (e: IllegalStateException) {
                0f
            }
        }.collect { offset ->
            isDeleteVisible = offset < -deleteButtonWidthPx / 2
        }
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(vertical = 8.dp)
    ) {
        AnimatedVisibility(
            visible = isDeleteVisible,
            enter = scaleIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            exit = scaleOut(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(50.dp)
                    .background(deleteButtonColor)
                    .border(width = borderWidth, color = shadowColor)
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete_forever),
                    contentDescription = "Delete",
                    tint = deleteIconColor
                )
            }
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .anchoredDraggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal
                )
                .offset {
                    val offsetX = try {
                        val offset = draggableState.requireOffset()
                        if (offset.isNaN()) 0 else offset.roundToInt()
                    } catch (e: Exception) {
                        0
                    }
                    IntOffset(offsetX, 0)
                }
        ) {
            NeoControlSurface(
                backgroundColor = backgroundColor,
                shadowColor = shadowColor,
                shadowOffset = shadowOffset,
                borderWidth = borderWidth,
                shape = shape
            ) {
                content()
            }
        }
    }
}