package com.mewa.flickrapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mewa.flickrapp.data.local.db.dao.CatDao
import com.mewa.flickrapp.data.local.db.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1)
abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao
}