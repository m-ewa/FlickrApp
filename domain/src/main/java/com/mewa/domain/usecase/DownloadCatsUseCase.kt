package com.mewa.domain.usecase

import com.mewa.domain.model.Cat
import com.mewa.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadCatsUseCase @Inject constructor(
    private val catsRepository: CatsRepository
) {

    suspend operator fun invoke(): Flow<Result<List<Cat>>> {
        return catsRepository.getCats()
    }
}