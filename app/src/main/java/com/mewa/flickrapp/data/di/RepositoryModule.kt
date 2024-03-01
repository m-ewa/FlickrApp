package com.mewa.flickrapp.data.di

import com.mewa.flickrapp.data.remote.repository.CatsRepositoryImpl
import com.mewa.flickrapp.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCatsRepository(
        catsRepositoryImpl: CatsRepositoryImpl
    ): CatsRepository
}