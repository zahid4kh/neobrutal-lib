package zahid.neobrutal.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    isEmailEnabled: Boolean = true
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
//        passwordValue = "",
//        onPasswordValueChange = {},
//        isPasswordError = isPasswordError,
//        isPasswordEnabled = true,
//        isEmailError = isEmailError,
//        emailValue = "",
//        onEmailValueChange = {},
//        isEmailEnabled = true
//    )
//}
