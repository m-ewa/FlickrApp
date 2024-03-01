package com.mewa.flickrapp.data.di

import android.app.Application
import androidx.room.Room
import com.mewa.flickrapp.data.local.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CatDatabase {
        return Room.databaseBuilder(
            app,
            CatDatabase::class.java,
            "cat_database"
        )
            .build()
    }
}