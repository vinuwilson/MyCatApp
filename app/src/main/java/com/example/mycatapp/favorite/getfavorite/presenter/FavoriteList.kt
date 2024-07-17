package com.example.mycatapp.favorite.getfavorite.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mycatapp.R
import com.example.mycatapp.utils.AppBar
import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails

@Composable
fun FavoriteList(
    navController: NavHostController?,
    onFavSelected: (String) -> Unit
) {
    Scaffold(topBar = {
        AppBar(
            title = stringResource(id = R.string.cat_favorite_list),
            icon = Icons.AutoMirrored.Filled.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val viewModel: FavoriteListViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.getFavorite()
            }
            Box(modifier = Modifier.fillMaxSize()) {
                if (state.value.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn {
                        val favList = state.value.favDetails.distinctBy {
                            it.image_id
                        }
                        items(favList) { fav ->
                            FavListView(fav, onFavSelected)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavListView(favList: FavDetails, onFavSelected: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_view_rounded_shape)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = R.dimen.card_view_card_elevation)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.card_view_padding))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(favList.image.url)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cat_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_app_padding))
                .clickable { onFavSelected(favList.image_id) }
        )
    }
}

@Composable
@Preview
fun FavoriteListPreview() {
    FavoriteList(
        navController = null,
        onFavSelected = {}
    )
}