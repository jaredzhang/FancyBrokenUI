package com.jaredzhang.fancybrokenui.net


import com.google.gson.Gson
import com.jaredzhang.fancybrokenui.MockData
import com.jaredzhang.fancybrokenui.data.ConfigProvider
import com.jaredzhang.fancybrokenui.di.ApiModule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Before
import org.junit.Test

class RestanrantApiTest {

    private val configProvider: ConfigProvider = mock()

    private lateinit var restaurantApi: RestaurantApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        whenever(configProvider.getRemoteUrl()).thenReturn(mockWebServer.url("").toString())

        restaurantApi = ApiModule().providesRestaurantApi(configProvider)
    }

    @Test
    fun testGetAllHomeItems() {

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(Gson().toJson(MockData.items)))

        restaurantApi.home()
                .test()
                .assertComplete()
                .assertValue(MockData.items)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
