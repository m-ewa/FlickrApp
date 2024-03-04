package com.mewa.domain.repository

import com.mewa.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRepository {

    suspend fun getCats(): Flow<Result<List<Cat>>>
}