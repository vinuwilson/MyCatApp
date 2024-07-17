package com.example.mycatapp.catinfo.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mycatapp.R
import com.example.mycatapp.favorite.addfavorite.presenter.AddToFavorite
import com.example.mycatapp.ui.theme.MyCatAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListView(
    catUiState: CatUiState,
    showFavoriteSelected: () -> Unit,
    onCatDetailsSelected: (String) -> Unit
) {
    var showFavorite by remember { 
        mutableStateOf(false) 
    }
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            Icon(
                Icons.Default.Home,
                contentDescription = stringResource(R.string.app_bar_image),
                Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.top_bar_horizontal_padding))
            )
        },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(stringResource(R.string.cat_home_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "abc",
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.top_bar_horizontal_padding))
                        .clickable(
                            onClick = {
                                showFavorite = true
                                showFavoriteSelected()
                            }
                        )
                )
            }
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (catUiState.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn {
                        items(catUiState.catDetails) { catDetails ->
                            CatItemView(catDetails, onCatDetailsSelected)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CatItemView(catDetails: ResultUiState, onCatDetailsSelected: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_view_rounded_shape)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = R.dimen.card_view_card_elevation)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.card_view_padding))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(catDetails.catImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cat_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_app_padding))
                .clickable {
                    onCatDetailsSelected(catDetails.catId)
                }
        )
        Row {
            FavoriteButton(catDetails.catId, favFlag = true)
            Text(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.default_app_padding)),
                text = catDetails.name!!,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun FavoriteButton(
    catId: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    favFlag : Boolean = false
) {

    var isFavorite by rememberSaveable { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

    if (isFavorite)
        AddToFavorite(catId)
}

@Preview(showBackground = true)
@Composable
fun CatListViewPreview() {
    MyCatAppTheme {
        CatListView(
            catUiState = CatUiState(
                listOf(
                    ResultUiState(
                        catId = "123",
                        catImageUrl = "http://cfa.org/Breeds/BreedsAB/Birman.aspx",
                        name = "Birman",
                        countryCode = "Fr",
                        description = "The Birman is a docile, quiet cat who loves people and will follow them from room to room. Expect the Birman to want to be involved in what you’re doing. He communicates in a soft voice, mainly to remind you that perhaps it’s time for dinner or maybe for a nice cuddle on the sofa. He enjoys being held and will relax in your arms like a furry baby.",
                        temperament = "Affectionate, Active, Gentle, Social",
                        origin = "France",
                        lifeSpan = "",
                        breedId = "",
                        weight = "10-15"
                    )
                ),
                loading = false
            ),
            showFavoriteSelected = {},
            onCatDetailsSelected = {}
        )
    }
}