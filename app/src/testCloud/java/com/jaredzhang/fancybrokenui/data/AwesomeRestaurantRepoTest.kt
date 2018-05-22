package com.jaredzhang.fancybrokenui.data

import com.jaredzhang.fancybrokenui.MockData
import com.jaredzhang.fancybrokenui.net.RestaurantApi
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test

class AwesomeRestaurantRepoTest {

    val api: RestaurantApi = mock()

    val repo: RestaurantRepo = AwesomeRestaurantRepo(api)

    @Test
    fun testGetAllHomeItems() {
        whenever(api.home()).thenReturn(Single.just(MockData.items))

        repo.getAllHomeItems()
                .test()
                .assertComplete()
                .assertValue(MockData.items)
    }
}