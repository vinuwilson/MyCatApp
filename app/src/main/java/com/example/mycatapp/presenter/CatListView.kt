package com.example.mycatapp.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mycatapp.R

@Composable
fun CatListView(
    catUiState: CatUiState,
    onCatDetailsSelected: (String) -> Unit
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
            Text(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.default_app_padding)),
                text = catDetails.name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

