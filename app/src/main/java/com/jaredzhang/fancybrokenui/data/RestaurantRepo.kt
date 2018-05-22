package com.jaredzhang.fancybrokenui.data

import com.jaredzhang.fancybrokenui.entity.HomeItem
import io.reactivex.Single

interface RestaurantRepo {
    fun getAllHomeItems() : Single<List<HomeItem>>
}