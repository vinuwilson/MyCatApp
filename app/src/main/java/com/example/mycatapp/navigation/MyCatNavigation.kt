package com.example.mycatapp.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
import com.example.mycatapp.catinfo.presenter.CatDetailsView
import com.example.mycatapp.catinfo.presenter.CatListView
import com.example.mycatapp.catinfo.presenter.MyCatViewModel
import com.example.mycatapp.favorite.getfavorite.presenter.FavoriteList

@Composable
fun MyCatNavigation() {
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
                if (!catListApiFlag) {
                    LaunchedEffect(Unit) {
                        viewModel.getAllCatList()
                        catListApiFlag = true
                    }
                }
                state.toUiState()?.let {
                    CatListView(catUiState = it, showFavoriteSelected = {
                        navController.navigate(FavoriteListScreen)
                    }) { catId ->
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
                val catDetails = state.toUiState()?.let { details ->
                    details.catDetails.find {
                        it.catId == args.catId
                    }
                }
                if (catDetails != null) {
                    CatDetailsView(
                        catDetails = catDetails,
                        navController = navController
                    )
                } else {
                    navController.navigateUp()
                    val context = LocalContext.current
                    LaunchedEffect(Unit) {
                        Toast.makeText(
                            context,
                            "Cat details not found!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        composable<FavoriteListScreen> {
            FavoriteList(navController) { favSelectedId ->
                navController.navigate(
                    CatDetailsScreen(
                        catId = favSelectedId
                    )
                )
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