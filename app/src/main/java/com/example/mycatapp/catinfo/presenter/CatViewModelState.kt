package com.example.mycatapp.catinfo.presenter

import com.example.mycatapp.catinfo.data.model.CatDetails

data class CatViewModelState(
    val catDetails: List<CatDetails>? = emptyList(),
    val loading: Boolean = false
) {
    fun toUiState() = catDetails?.let {
        CatUiState(
            catDetails = it.map { result ->
                val breedDetails = result.listOfBreeds?.firstOrNull()
                ResultUiState(
                    catId = result.catId,
                    catImageUrl = result.catImageUrl,
                    name = breedDetails?.name,
                    countryCode = breedDetails?.countryCode,
                    description = breedDetails?.description,
                    temperament = breedDetails?.temperament,
                    origin = breedDetails?.origin,
                    lifeSpan = breedDetails?.lifeSpan,
                    breedId = breedDetails?.breedId,
                    weight = breedDetails?.weight?.weightInPounds
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
    val name: String? = "",
    val countryCode: String? = "",
    val description: String? = "",
    val temperament: String? = "",
    val origin: String? = "",
    val lifeSpan: String? = "",
    val breedId: String? = "",
    val weight: String? = "",

)