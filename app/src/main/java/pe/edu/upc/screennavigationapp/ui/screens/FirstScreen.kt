package pe.edu.upc.screennavigationapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import pe.edu.upc.screennavigationapp.navigation.AppScreens
import pe.edu.upc.screennavigationapp.ui.theme.ScreenNavigationAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "FirstScreen")
        }
    }) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Hello navigator")
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route + "/This is a parameter")
        }) {
            Text(text = "Navigate")
        }

        Button(onClick = {
            navController.navigate(route = AppScreens.AuthScreen.route)
        }) {
            Text(text = "Authentication")
        }

        Button(onClick = {
            navController.navigate(route = AppScreens.MessageScreen.route)
        }) {
            Text(text = "Messages")
        }
    }
}