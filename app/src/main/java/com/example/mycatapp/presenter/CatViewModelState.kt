package com.example.mycatapp.presenter

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.data.model.Weight

data class CatViewModelState(
    val catDetails: List<CatDetails>? = emptyList(),
    val loading: Boolean = false
) {
    fun toUiState() = catDetails?.let {
        CatUiState(
            catDetails = it.map { result ->
                ResultUiState(
                    catId = result.catId,
                    catImageUrl = result.catImageUrl,
                    name = result.listOfBreeds.first().name,
                    countryCode = result.listOfBreeds.first().countryCode,
                    description = result.listOfBreeds.first().description,
                    temperament = result.listOfBreeds.first().temperament,
                    origin = result.listOfBreeds.first().origin,
                    lifeSpan = result.listOfBreeds.first().lifeSpan,
                    breedId = result.listOfBreeds.first().breedId,
                    weight = result.listOfBreeds.first().weight.weightInPounds
                )
            },
            loading = loading
        )
    }
}

data class CatUiState(
    val catDetails: List<ResultUiState> = emptyList(),
    val loading: Boolean = false
)

data class ResultUiState(
    val catId: String,
    val catImageUrl: String,
    val name: String,
    val countryCode: String,
    val description: String,
    val temperament: String,
    val origin: String,
    val lifeSpan: String,
    val breedId: String,
    val weight: String,

)