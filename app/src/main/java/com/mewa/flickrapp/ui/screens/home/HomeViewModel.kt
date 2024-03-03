package com.mewa.flickrapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mewa.domain.usecase.DownloadCatsUseCase
import com.mewa.flickrapp.ui.mapper.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val downloadCatsUseCase: DownloadCatsUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        downloadCats()
    }

    fun downloadCats() {
        viewModelScope.launch {
            try {
                _homeUiState.value = HomeUiState.Loading
                downloadCatsUseCase()
                    .collect { result ->
                        result.onSuccess { cats ->
                            if (cats.isEmpty()) {
                                _homeUiState.value = HomeUiState.Failure()
                            } else {
                                _homeUiState.value = HomeUiState.Success(
                                    cats = cats.map { it.toUiState() }
                                )
                            }
                        }
                        result.onFailure { exception ->
                            _homeUiState.value =
                                HomeUiState.Failure(errorMessage = exception.message)
                        }
                    }
            } catch (e: Exception) {
                _homeUiState.value = HomeUiState.Failure(errorMessage = e.message)
            }
        }
    }
}