package com.jaredzhang.fancybrokenui.di

import com.jaredzhang.fancybrokenui.data.*
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun repository(): RestaurantRepo
}


@Module
class AppModule {
    @Provides @Singleton fun providesRestaurantRepository(): RestaurantRepo {
        return FakeRestaurantRepo()
    }
}

