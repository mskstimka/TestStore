package com.test.teststore.data.di

import com.test.teststore.data.local.LocalDataSourceImpl
import com.test.teststore.data.network.NetworkDataSourceImpl
import com.test.teststore.data.repositories.StoreRepositoryImpl
import com.test.teststore.domain.interfaces.LocalDataSource
import com.test.teststore.domain.interfaces.NetworkDataSource
import com.test.teststore.domain.interfaces.StoreRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsDataModule {

    @Binds
    fun bindDataRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}