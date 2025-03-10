package com.test.teststore.app.di

import android.app.Application
import android.content.Context
import com.test.teststore.app.App
import com.test.teststore.app.screens.details.DetailsFragment
import com.test.teststore.app.screens.local.LocalProductsFragment
import com.test.teststore.app.screens.main.MainFragment
import com.test.teststore.data.di.DataModule
import com.test.teststore.data.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(localProductsFragment: LocalProductsFragment)

    fun inject(app: App)

    @Component.Builder
    interface ComponentBuilder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): ComponentBuilder
    }
}
