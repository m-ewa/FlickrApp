package com.mewa.flickrapp.data.remote.repository

import androidx.room.withTransaction
import com.mewa.flickrapp.data.local.db.CatDatabase
import com.mewa.flickrapp.data.mapper.toDomain
import com.mewa.flickrapp.data.mapper.toEntity
import com.mewa.flickrapp.data.remote.FlickrApi
import com.mewa.flickrapp.data.remote.dto.CatsDto
import com.mewa.flickrapp.domain.repository.CatsRepository
import com.mewa.flickrapp.domain.repository.NetworkRepository
import com.mewa.flickrapp.extensions.parseDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatsRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val database: CatDatabase,
    private val networkRepository: NetworkRepository
) : CatsRepository {

    private val catDao = database.catDao()
    override suspend fun getCats() = networkBoundResource(
        query = {
            catDao.getAllCats().map { catsEntity ->
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
                    catDao.deleteAllCats()
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

        val isDatabaseEmpty = catDao.getNumberOfCats() == 0
        val isNetworkAvailable = networkRepository.isNetworkAvailable()

        if (!isDatabaseEmpty) {
            val data = query().first()
            emit(Result.success(data))
        }

        if (isNetworkAvailable) {
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

    private suspend fun saveCats(cats: List<CatsDto.CatDto>) {
        val sortedCatsDto = cats.sortedByDescending {
            it.publishedString?.let { published ->
                published.parseDate()
            }
        }
        catDao.insertCats(
            sortedCatsDto.map { catDto ->
                catDto.toEntity()
            }
        )
    }
}