package com.jaredzhang.fancybrokenui.net

import com.jaredzhang.fancybrokenui.entity.HomeItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

interface RestaurantApi {
    @GET("/home")
    fun home(): Single<List<HomeItem>>
}