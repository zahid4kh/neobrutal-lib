package zahid.neobrutal.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * An email field stacked above a password field.
 *
 * Each field keeps its own value, change callback, error state, and enabled flag.
 * The email label is "Email" and the password label is "Password".
 *
 * @param modifier Modifier to be applied to the column that holds both fields
 * @param passwordValue Current password text
 * @param onPasswordValueChange Callback invoked when the password text changes
 * @param isPasswordError State set to true when the password contains a space
 * @param isPasswordEnabled Whether the password field accepts input
 * @param isEmailError State set to true when the email does not contain `@`
 * @param emailValue Current email text
 * @param onEmailValueChange Callback invoked when the email text changes
 * @param isEmailEnabled Whether the email field accepts input
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