package zahid.neobrutal.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * An email&password text field component in NeoBrutal style.
 *
 * This text field features thick borders, sharp corners, and a monospaced font
 * following NeoBrutal design principles. It includes options for labels, icons,
 * and changes border color on focus to provide clear visual feedback.
 *
 * @param modifier Modifier to be applied to the text field
 * @param passwordValue Current password text value
 * @param onPasswordValueChange Callback invoked when the password text changes
 * @param isPasswordError Whether the password text field has an error
 * @param isPasswordEnabled Whether the password text field is enabled
 * @param isEmailError Whether the email text field has an error
 * @param emailValue Current email text value
 * @param onEmailValueChange Callback invoked when the email text changes
 * @param isEmailEnabled Whether the email text field is enabled
 * @param shadowColor Color of the NeoBrutal shadow effect
 * @param shadowOffset How far the shadow is offset from the text field
 * @param borderWidth Width of the text field border
 * @param shape Shape of the text field
 */


@Composable
fun NeoEmailPasswordTextField(
    modifier: Modifier = Modifier,
    passwordValue: String,
    onPasswordValueChange: (String) -> Unit,
    isPasswordError: MutableState<Boolean>,
    isPasswordEnabled: Boolean = true,
    isEmailError: MutableState<Boolean>,
    emailValue: String,
    onEmailValueChange: (String) -> Unit,
    isEmailEnabled: Boolean = true,
    shadowColor: Color = Color.Black,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape
){
    Column(
        modifier = modifier
    ){

        NeoEmailTextField(
            value = emailValue,
            onValueChange = onEmailValueChange,
            label = "Email",
            placeholder = "Enter your email",
            isError = isEmailError,
            enabled = isEmailEnabled
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        NeoPasswordTextField(
            value = passwordValue,
            onValueChange = onPasswordValueChange,
            label = "Password",
            placeholder = "Enter your password",
            isError = isPasswordError,
            enabled = isPasswordEnabled
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NeoEmailPasswordTextFieldPreview(){
//    var isPasswordError = remember { mutableStateOf(false) }
//    var isEmailError = remember { mutableStateOf(false) }
//
//    NeoEmailPasswordTextField(
//        passwordValue = helloworld,
//        onPasswordValueChange = {},
//        isPasswordError = isPasswordError,
//        isPasswordEnabled = true,
//        isEmailError = isEmailError,
//        emailValue = exampleemail@gmail.com,
//        onEmailValueChange = {},
//        isEmailEnabled = true
//    )
//}
