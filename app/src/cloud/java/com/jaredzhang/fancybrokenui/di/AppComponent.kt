package com.jaredzhang.fancybrokenui.di

import com.jaredzhang.fancybrokenui.data.AwesomeConfigProvider
import com.jaredzhang.fancybrokenui.data.AwesomeRestaurantRepo
import com.jaredzhang.fancybrokenui.data.ConfigProvider
import com.jaredzhang.fancybrokenui.data.RestaurantRepo
import com.jaredzhang.fancybrokenui.net.RestaurantApi

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun repository(): RestaurantRepo
}


@Module
class AppModule {
    @Provides @Singleton fun providesRestaurantRepository(restaurantApi: RestaurantApi): RestaurantRepo {
        return AwesomeRestaurantRepo(restaurantApi)
    }

    @Provides @Singleton fun providesConfig(): ConfigProvider {
        return AwesomeConfigProvider()
    }
}

@Module
class ApiModule {
    @Provides @Singleton fun providesRestaurantApi(configProvider: ConfigProvider): RestaurantApi {
        return Retrofit.Builder()
                .baseUrl(configProvider.getRemoteUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestaurantApi::class.java)
    }
}
