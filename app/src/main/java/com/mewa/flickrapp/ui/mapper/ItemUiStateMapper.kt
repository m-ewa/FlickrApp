package com.mewa.flickrapp.ui.mapper

import com.mewa.domain.model.Cat
import com.mewa.flickrapp.ui.item.ItemUiState

fun Cat.toUiState(): ItemUiState {
    return ItemUiState(
        imageUrl = imageUrl ?: "",
        description = description ?: "",
        link = link ?: "",
    )
}