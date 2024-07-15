package com.example.mycatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.mycatapp.presenter.CatDetailsView
import com.example.mycatapp.presenter.CatListView
import com.example.mycatapp.presenter.MyCatViewModel
import com.example.mycatapp.ui.theme.MyCatAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCatAppTheme {
                MyCatAppNav()
            }
        }
    }
}

@Composable
fun MyCatAppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CatInformation
    ) {
        navigation<CatInformation>(
            startDestination = CatListScreen
        ) {
            composable<CatListScreen> { entry ->
                val viewModel = entry.sharedViewModel<MyCatViewModel>(navController = navController)
                val state by viewModel.state.collectAsStateWithLifecycle()
                var catListApiFlag by rememberSaveable { mutableStateOf(false) }
                if(!catListApiFlag) {
                    LaunchedEffect(Unit) {
                        viewModel.getAllCatList()
                        catListApiFlag = true
                    }
                }
                state.toUiState()?.let {
                    CatListView(it) { catId ->
                        navController.navigate(
                            CatDetailsScreen(
                                catId = catId
                            )
                        )
                    }
                }
            }

            composable<CatDetailsScreen> { entry ->
                val viewModel = entry.sharedViewModel<MyCatViewModel>(navController = navController)
                val state by viewModel.state.collectAsStateWithLifecycle()

                val args = entry.toRoute<CatDetailsScreen>()
                state.toUiState()?.let { details ->
                    CatDetailsView(
                        args.catId,
                        details.catDetails
                    )
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

@Serializable
object CatListScreen

@Serializable
data class CatDetailsScreen(
    val catId: String,
)

@Serializable
object CatInformation

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCatAppTheme {
        MyCatAppNav()
    }
}