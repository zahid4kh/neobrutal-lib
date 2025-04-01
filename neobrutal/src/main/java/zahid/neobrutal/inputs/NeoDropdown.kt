package zahid.neobrutal.inputs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex

/**
 * A dropdown menu component in NeoBrutal style.
 *
 * This dropdown features a solid rectangular button that opens a list of items with
 * thick borders and high contrast. It follows the NeoBrutal design principles with
 * sharp corners, bold typography, and the characteristic shadow effect.
 *
 * @param items List of items to display in the dropdown
 * @param selectedIndex The index of the currently selected item
 * @param onItemSelected Callback invoked when an item is selected
 * @param modifier Modifier to be applied to the dropdown
 * @param label Optional label to display above the dropdown
 * @param enabled Whether the dropdown is enabled
 * @param backgroundColor Background color of the dropdown button
 * @param selectedItemColor Background color of the selected item
 * @param textColor Color of the button and item text
 * @param labelColor Color of the label text
 * @param borderColor Color of the dropdown borders
 * @param shadowColor Color of the NeoBrutal shadow effect
 * @param shadowOffset How far the shadow is offset from the dropdown
 * @param borderWidth Width of the dropdown borders
 * @param shape Shape of the dropdown components
 * @param maxHeight Maximum height of the expanded dropdown list
 * @param itemHeight Height of each dropdown item
 */
@Composable
fun <T> NeoDropdown(
    items: List<T>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    selectedItemColor: Color = Color(0xFFFF5470),
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    labelColor: Color = MaterialTheme.colorScheme.onBackground,
    borderColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape,
    maxHeight: Dp = 200.dp,
    itemHeight: Dp = 48.dp
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "rotation"
    )

    Column(modifier = modifier.wrapContentSize()) {
        if (label != null) {
            Text(
                text = label,
                color = labelColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .height(itemHeight)
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(shadowColor, shape)
            )

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .height(itemHeight)
                    .background(backgroundColor, shape)
                    .border(width = borderWidth, color = borderColor, shape = shape)
                    .clickable(enabled = enabled) {
                        expanded = !expanded
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (items.isNotEmpty() && selectedIndex in items.indices)
                            items[selectedIndex].toString() else "Select an item",
                        color = textColor,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand",
                        tint = textColor,
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(rotationState)
                    )
                }
            }
        }

        if (expanded) {
            Popup(
                alignment = Alignment.TopCenter,
                offset = IntOffset(0, 220),
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(220.dp)
                        .offset(x = shadowOffset, y = shadowOffset)
                        .background(shadowColor, shape)
                        .zIndex(0f)

                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .fillMaxWidth()
                            .clip(shape)
                            .background(backgroundColor)
                            .border(width = borderWidth, color = borderColor, shape = shape),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .wrapContentWidth()
                        ) {
                            item{
                                items.forEachIndexed { index, item ->
                                    val isSelected = index == selectedIndex

                                    Box(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .fillMaxWidth()
                                            .height(itemHeight)
                                            .background(
                                                if (isSelected) selectedItemColor else backgroundColor
                                            )
                                            .clickable {
                                                onItemSelected(index)
                                                expanded = false
                                            }
                                            .padding(horizontal = 16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = item.toString(),
                                            color = if (isSelected) Color.White else textColor,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 16.sp
                                        )
                                    }

                                    if (index < items.size - 1) {
                                        HorizontalDivider(
                                            thickness = 1.dp,
                                            color = borderColor.copy(alpha = 0.3f)
                                        )
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}