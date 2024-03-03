package com.mewa.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mewa.data.local.db.converter.Converters
import com.mewa.data.local.db.dao.CatDao
import com.mewa.data.local.db.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun catDao(): CatDao
}