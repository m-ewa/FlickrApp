package com.mewa.flickrapp.domain.repository

import com.mewa.flickrapp.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRepository {

    suspend fun getCats(): Flow<Result<List<Cat>>>
}