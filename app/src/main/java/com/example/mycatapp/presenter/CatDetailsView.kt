package com.example.mycatapp.presenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mycatapp.R
import com.example.mycatapp.ui.theme.MyCatAppTheme

@Composable
fun CatDetailsView(catId: String, catDetails: List<ResultUiState>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_app_padding))
        ) {
            catDetails.map { details ->
                if (catId == details.catId) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        CatDetailsSection(details)
                    }
                }
            }
        }
    }
}

@Composable
fun CatDetailsSection(details: ResultUiState) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(details.catImageUrl)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.cat_image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.default_app_padding))
    )

    Text(
        text = details.name,
        style = MaterialTheme.typography.displaySmall
    )
    RowTextFields("Origin", details.origin)
    RowTextFields("Temperament", details.temperament)
    RowTextFields("LifeSpan", details.lifeSpan)
    RowTextFields("Weight in Pounds", details.weight)
    Text(
        modifier = Modifier.alpha(0.7f),
        text = details.description,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun RowTextFields(title: String, textValue: String) {
    Row {
        Text(
            text = "${title}:-",
            modifier = Modifier.alpha(0.7f),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_width)))
        Text(
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            text = textValue,
            style = MaterialTheme.typography.titleMedium
        )
    }
    Spacer(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.spacer_height))
    )
}

@Preview(showBackground = true)
@Composable
fun CatDetailsSectionPreview() {
    MyCatAppTheme {
        CatDetailsSection(
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
        )
    }
}