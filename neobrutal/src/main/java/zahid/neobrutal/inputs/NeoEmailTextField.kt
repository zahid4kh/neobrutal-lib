package zahid.neobrutal.inputs

import android.R.attr.fontFamily
import android.R.attr.fontWeight
import android.R.attr.singleLine
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zahid.neobrutal.R

/**
 * An email text field component in NeoBrutal style.
 *
 * This text field features thick borders, sharp corners, and a monospaced font
 * following NeoBrutal design principles. It includes options for labels, icons,
 * and changes border color on focus to provide clear visual feedback.
 *
 * @param value Current text value
 * @param onValueChange Callback invoked when the text changes
 * @param modifier Modifier to be applied to the text field
 * @param label Optional label to display above the text field
 * @param placeholder Optional placeholder text displayed when the field is empty
 * @param errorMessage Optional error message to display when the input is invalid
 * @param enabled Whether the text field is enabled
 * @param isError Whether the text field has an error
 * @param readOnly Whether the text field is read-only
 * @param keyboardOptions Options controlling keyboard behavior
 * @param keyboardActions Actions to perform based on keyboard input
 * @param visualTransformation Visual transformation of the text (e.g., for password fields)
 * @param interactionSource Source of interactions for the text field
 * @param textStyle Style of the input text
 * @param backgroundColor Background color of the text field
 * @param focusedBorderColor Border color when the text field is focused
 * @param unfocusedBorderColor Border color when the text field is not focused
 * @param labelColor Color of the label text
 * @param placeholderColor Color of the placeholder text
 * @param iconTint Tint color for the icons
 * @param shadowColor Color of the NeoBrutal shadow effect
 * @param shadowOffset How far the shadow is offset from the text field
 * @param borderWidth Width of the text field border
 * @param shape Shape of the text field
 */
@Composable
fun NeoEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = "Email",
    placeholder: String? = "Enter your email",
    errorMessage: String = "Email must contain `@`",
    enabled: Boolean = true,
    isError: MutableState<Boolean>,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    backgroundColor: Color = Color.White,
    focusedBorderColor: Color = Color(0xFF521922),
    unfocusedBorderColor: Color = Color.Black,
    labelColor: Color = Color.Black,
    placeholderColor: Color = Color.Gray,
    iconTint: Color = Color.Black,
    shadowColor: Color = Color.Black,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor = when {
        isError.value -> Color.Red
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }

    LaunchedEffect(value) {
        isError.value = !value.contains("@")
    }

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                color = labelColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(shadowColor, shape)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle.copy(color = if (enabled) Color.Black else Color.Gray),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                singleLine = true,
                cursorBrush = SolidColor(focusedBorderColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor, shape)
                    .border(width = borderWidth, color = borderColor, shape = shape),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty() && placeholder != null) {
                                Text(
                                    text = placeholder,
                                    color = placeholderColor,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )
        }
        if(isError.value){
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 6.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal
                ),
            )
        }
    }
}