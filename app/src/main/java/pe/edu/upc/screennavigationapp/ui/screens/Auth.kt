package pe.edu.upc.screennavigationapp.ui.screens

import android.annotation.SuppressLint
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthScreen(activity: FragmentActivity, navController: NavController) {
    //var authVerify = remember { mutableStateOf(false) }
    var authVerify by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        TopAppBar() {
            if (authVerify){
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, null)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = "AuthScreen")
        }
    }) {
        Auth(activity = activity, onDataChanged = {data -> authVerify = data})
    }
}

@Composable
fun Auth(activity: FragmentActivity, onDataChanged: (Boolean) -> Unit){
    //var auth = remember { mutableStateOf(false) }
    var auth by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(if (auth) Color.Green else Color.Red)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = if (auth) "You're authenticated" else "You new authentication", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (auth) {
                auth = false
                onDataChanged(auth)
            } else {
                authenticate ({ auth = true; onDataChanged(auth)}, activity )
            }
        }) {
            Text(text = if (auth) "Close" else "Authentication")
        }
    }
    setupAuth(activity)
}

private var canAuthenticate =  false
private lateinit var promptInfo: BiometricPrompt.PromptInfo

private fun setupAuth(activity: FragmentActivity) {
    if( BiometricManager.from(activity).canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        ) == BiometricManager.BIOMETRIC_SUCCESS){
        canAuthenticate = true
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Authentication is using biometric sensor")
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG
                        or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()
    }
}

private fun authenticate(auth: (auth: Boolean) -> Unit, activity: FragmentActivity) {
    if (canAuthenticate){
        BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
            object: BiometricPrompt.AuthenticationCallback() {
                override  fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    auth(true)
                }
            }).authenticate(promptInfo)

    } else {
        auth(true)
    }
}


/*
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ScreenNavigationAppTheme {
        Auth(activity)
    }
}*/
