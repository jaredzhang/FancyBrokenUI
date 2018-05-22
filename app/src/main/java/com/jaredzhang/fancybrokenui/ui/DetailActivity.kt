package com.jaredzhang.fancybrokenui.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jaredzhang.fancybrokenui.R
import com.jaredzhang.fancybrokenui.entity.HomeItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val homeItem = intent.getParcelableExtra<HomeItem>("homeItem")

        if (homeItem.thumnbnailUrl.isNotBlank()) {
            Picasso.with(this).load(homeItem.thumnbnailUrl).into(ivThumbnail)
        } else {
            ivThumbnail.setImageResource(R.drawable.placeholder)
        }
        tvTitle.text = homeItem.title
        tvDescription.text = homeItem.description

    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }
}