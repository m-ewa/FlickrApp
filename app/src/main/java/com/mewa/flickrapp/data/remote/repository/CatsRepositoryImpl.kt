package com.mewa.flickrapp.data.remote.repository

import androidx.room.withTransaction
import com.mewa.flickrapp.data.local.db.CatDatabase
import com.mewa.flickrapp.data.mapper.toDomain
import com.mewa.flickrapp.data.mapper.toEntity
import com.mewa.flickrapp.data.remote.FlickrApi
import com.mewa.flickrapp.data.remote.dto.CatsDto
import com.mewa.flickrapp.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatsRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val database: CatDatabase
) : CatsRepository {

    private val catDao = database.catDao()

    override fun getCats() = networkBoundResource(
        query = {
            catDao.getAllCats().map { catsEntity ->
                catsEntity.map { catEntity ->
                    catEntity.toDomain()
                }
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
        },
        shouldFetch = { it.isEmpty() }
    )

    private inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit,
        crossinline shouldFetch: (ResultType) -> Boolean
    ) = flow {
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            try {
                saveFetchResult(fetch())
                query().map { Result.success(it) }
            } catch (throwable: Throwable) {
                query().map { Result.failure(throwable) }
            }
        } else {
            query().map { Result.success(it) }
        }

        emitAll(flow)
    }

    private suspend fun saveCats(cats: List<CatsDto.CatDto>) {
        val sortedCatsDto = cats.sortedByDescending {
            it.publishedString?.let { published ->
                parseDateString(published)
            }
        }
        catDao.insertCats(
            sortedCatsDto.mapIndexed { id, catDto ->
                catDto.toEntity(id)
            }
        )
    }

    private fun parseDateString(dateString: String): Date? {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss"
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.parse(dateString)
    }
}