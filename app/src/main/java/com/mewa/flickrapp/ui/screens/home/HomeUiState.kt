package com.mewa.flickrapp.ui.screens.home

import com.mewa.flickrapp.ui.item.ItemUiState

sealed class HomeUiState {

    object Loading : HomeUiState()

    data class Success(
        val cats: List<ItemUiState>
    ) : HomeUiState()

    data class Failure(
        val errorMessage: String? = null
    ) : HomeUiState()
}