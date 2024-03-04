package com.mewa.data.di

import com.mewa.data.remote.repository.CatsRepositoryImpl
import com.mewa.data.remote.repository.NetworkRepositoryImpl
import com.mewa.domain.repository.CatsRepository
import com.mewa.domain.repository.NetworkRepository
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

    @Binds
    abstract fun bindNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository
}