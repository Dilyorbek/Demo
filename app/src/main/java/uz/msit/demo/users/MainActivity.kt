package uz.msit.demo.users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uz.msit.demo.ui.theme.DemoTheme
import uz.msit.demo.users.presentation.user_details.UserDetailsScreen
import uz.msit.demo.users.presentation.user_details.UserDetailsViewModel
import uz.msit.demo.users.presentation.user_list.UsersScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "users"
                    ) {
                        composable(route = "users") {
                            UsersScreen({
                                navController.navigate("details?login=$it")
                            })
                        }
                        composable(
                            route = "details?login={login}",
                            arguments = listOf(
                                navArgument("login") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            UserDetailsScreen({
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}