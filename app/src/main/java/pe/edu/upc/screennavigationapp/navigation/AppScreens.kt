package pe.edu.upc.screennavigationapp.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object AuthScreen: AppScreens("auth_screen")
    object MessageScreen: AppScreens("message_screen")
}
