package com.test.teststore.data.di

import android.content.Context
import com.test.teststore.data.local.StoreDao
import com.test.teststore.data.local.StoreDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): StoreDatabase {
        return StoreDatabase.getDatabase(context)
    }

    @Provides
    fun provideStoreDao(database: StoreDatabase): StoreDao {
        return database.storeDao()
    }
}

