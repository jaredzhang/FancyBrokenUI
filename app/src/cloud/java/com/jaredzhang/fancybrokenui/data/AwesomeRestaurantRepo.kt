package com.jaredzhang.fancybrokenui.data


import com.jaredzhang.fancybrokenui.entity.HomeItem
import com.jaredzhang.fancybrokenui.net.RestaurantApi
import io.reactivex.Single

class AwesomeRestaurantRepo(val restaurantApi: RestaurantApi) : RestaurantRepo {

    override fun getAllHomeItems(): Single<List<HomeItem>> {
        // TODO add cache layer
        return restaurantApi.home()
    }
}