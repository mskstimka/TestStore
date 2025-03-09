package com.test.teststore.data.di

import com.google.gson.GsonBuilder
import com.test.teststore.data.network.NetworkDataSourceImpl
import com.test.teststore.data.network.StoreAPI
import com.test.teststore.domain.STORE_URL
import com.test.teststore.domain.interfaces.NetworkDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule() {

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()


    @Provides
    fun provideRetrofitProvider(client: OkHttpClient): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(STORE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideNetworkDataSource(
        retrofit: Retrofit,
    ): NetworkDataSource {
        return NetworkDataSourceImpl(
            storeApi = retrofit.create(StoreAPI::class.java),
        )
    }

    @Provides
    fun provideStoreApi(retrofit: Retrofit): StoreAPI {
        return retrofit.create(StoreAPI::class.java)
    }
}