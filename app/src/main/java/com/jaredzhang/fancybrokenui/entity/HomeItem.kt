package com.jaredzhang.fancybrokenui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeItem(val id: Int, val thumnbnailUrl: String, val title: String, val description: String? = null) : Parcelable
