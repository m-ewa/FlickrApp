package com.mewa.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mewa.data.local.db.entity.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Query("SELECT * FROM cats")
    fun getAllCats(): Flow<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCats(cats: List<CatEntity>)

    @Query("DELETE FROM cats")
    suspend fun deleteAllCats()

    @Query("SELECT COUNT(*) FROM cats")
    suspend fun getNumberOfCats(): Int
}