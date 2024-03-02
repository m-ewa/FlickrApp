package com.mewa.flickrapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mewa.flickrapp.data.local.db.converter.Converters
import com.mewa.flickrapp.data.local.db.dao.CatDao
import com.mewa.flickrapp.data.local.db.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao
}