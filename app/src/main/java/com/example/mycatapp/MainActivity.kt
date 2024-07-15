package com.example.mycatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycatapp.presenter.CatListView
import com.example.mycatapp.presenter.CatUiState
import com.example.mycatapp.presenter.MyCatViewModel
import com.example.mycatapp.ui.theme.MyCatAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
    val viewModel : MyCatViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getAllCatList()
    }
    state.value.toUiState()?.let { CatListView(it) }
//    state.value.toUiState()?.let { Greeting1(it) }
}


@Composable
fun Greeting1(state: CatUiState) {
LazyColumn {
    items(state.catDetails) {
       Greeting(name = it.catId)
    }
}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCatAppTheme {
        Greeting("Android")
    }
}