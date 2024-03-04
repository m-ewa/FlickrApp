package com.mewa.data.remote.repository

import androidx.room.withTransaction
import com.mewa.data.extensions.parseDate
import com.mewa.data.mapper.toDomain
import com.mewa.data.mapper.toEntity
import com.mewa.domain.repository.CatsRepository
import com.mewa.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatsRepositoryImpl @Inject constructor(
    private val flickrApi: com.mewa.data.remote.FlickrApi,
    private val database: com.mewa.data.local.db.Database,
    private val networkRepository: NetworkRepository
) : CatsRepository {

    private val dao = database.catDao()
    override suspend fun getCats() = networkBoundResource(
        query = {
            dao.getAllCats().map { catsEntity ->
                catsEntity
                    .sortedByDescending { it.published }
                    .map { catEntity -> catEntity.toDomain() }
            }
        },
        fetch = {
            flickrApi.downloadCats().body()?.items
        },
        saveFetchResult = { catsDto ->
            val filteredCats = catsDto?.filter { !it.media?.imageUrl.isNullOrEmpty() }
            if (!filteredCats.isNullOrEmpty()) {
                database.withTransaction {
                    dao.deleteAllCats()
                    saveCats(filteredCats)
                }
            }
        }
    )

    private inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit
    ) = flow {

        val isDatabaseEmpty = dao.getNumberOfCats() == 0

        if (dao.getNumberOfCats() != 0) {
            val data = query().first()
            emit(Result.success(data))
        }

        if (networkRepository.isNetworkAvailable()) {
            try {
                val fetchedData = fetch()
                saveFetchResult(fetchedData)
                emitAll(query().map { Result.success(it) })
            } catch (throwable: Throwable) {
                emit(Result.failure(throwable))
            }
        } else if (isDatabaseEmpty) {
            emit(Result.failure(Throwable("No internet connection")))
        }
    }

    private suspend fun saveCats(cats: List<com.mewa.data.remote.dto.CatsDto.CatDto>) {
        val sortedCatsDto = cats.sortedByDescending {
            it.publishedString?.parseDate()
        }
        dao.insertCats(
            sortedCatsDto.map { catDto ->
                catDto.toEntity()
            }
        )
    }
}