package zahid.neobrutal.cards

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

enum class DragState { NotSwiped, Swiped }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NeoSwipeableCard(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
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
                println("Error in NeoSwipeableCard: ${e.message}")
                0f
            }
        }.collect { offset ->
            isDeleteVisible = offset < -deleteButtonWidthPx / 2
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        if (isDeleteVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .size(50.dp)
                    .zIndex(2f) // above the card so it can be clickedd
                    .background(Color.Red.copy(alpha = 0.7f), shape = RoundedCornerShape(25.dp))
                    .clickable {
                        println("Delete button clicked")
                        onDelete()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f) // below the delete button
                .background(Color.Red.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .anchoredDraggable(
                        state = draggableState,
                        orientation = Orientation.Horizontal
                    )
                    .offset {
                        IntOffset(
                            try {
                                draggableState.requireOffset().roundToInt()
                            } catch (e: IllegalStateException) {
                                println("Error in NeoSwipeableCard: ${e.message}")
                                0
                            },
                            0
                        )
                    }
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            ) {
                content()
            }
        }
    }
}