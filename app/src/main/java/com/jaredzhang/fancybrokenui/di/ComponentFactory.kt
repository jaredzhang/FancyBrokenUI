package com.jaredzhang.fancybrokenui.di

object ComponentFactory {
    var appComponent = DaggerAppComponent.builder().build()
}