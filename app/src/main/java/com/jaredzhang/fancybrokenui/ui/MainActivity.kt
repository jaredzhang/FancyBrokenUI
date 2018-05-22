package com.jaredzhang.fancybrokenui.ui

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

import com.github.techisfun.android.topsheet.TopSheetBehavior
import com.jaredzhang.fancybrokenui.R
import com.jaredzhang.fancybrokenui.di.ComponentFactory
import com.jaredzhang.fancybrokenui.entity.HomeItem
import com.jaredzhang.fancybrokenui.ui.adapter.HomeItemAapter
import com.jaredzhang.fancybrokenui.ui.adapter.HomeItemClickListener
import com.jaredzhang.fancybrokenui.util.Utils
import kotlinx.android.synthetic.main.activity_main.*

import android.util.Pair as UtilPair


class MainActivity : AppCompatActivity(), HomeItemClickListener {

    internal var translateY: Int = 0

    val homeItemAapter = HomeItemAapter(this)

    val heroImageMultiplier = 0.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.image).addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)
                translateY = v.height - resources.getDimensionPixelSize(R.dimen.peak_height)
                v.translationY = translateY * heroImageMultiplier
            }
        })

        val listTranslate = resources.getDimensionPixelOffset(R.dimen.list_translate)
        val sheet = findViewById<View>(R.id.top_sheet)
        TopSheetBehavior.from(sheet).setTopSheetCallback(object : TopSheetBehavior.TopSheetCallback() {
            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    TopSheetBehavior.STATE_EXPANDED -> {
                        homeItemAapter.isActivated = false
                    }
                    TopSheetBehavior.STATE_COLLAPSED -> {
                        homeItemAapter.isActivated = true
                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                findViewById<View>(R.id.image).translationY = (1 - slideOffset) * translateY * heroImageMultiplier
                rvHome.translationY = slideOffset * listTranslate
                rvHome.clipPercent = slideOffset
            }
        })

        rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvHome.adapter = homeItemAapter
        rvHome.clipHeight = resources.getDimensionPixelSize(R.dimen.image_height)


        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.liveItems.observe(this, Observer {
            it?.let {
                homeItemAapter.homeItems = it
            }
        })
    }

    override fun onItemClick(view: View, position: Int, homeItem: HomeItem) {

        val intent = Intent(this, DetailActivity::class.java).putExtra("homeItem", homeItem)
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="robot"
        val options = ActivityOptions
                .makeSceneTransitionAnimation(this,
                        UtilPair.create(view.findViewById(R.id.ivThumbnail), "transition_thumbnail"),
                        UtilPair.create(view.findViewById(R.id.tvTitle), "transition_title"),
                        UtilPair.create(view.findViewById(R.id.tvDescription), "transition_description")
                )
        // start the new activity
        startActivity(intent, options.toBundle())
    }

}

class MainActivityViewModel: ViewModel() {

    val liveItems = MutableLiveData<List<HomeItem>>()

    init {
        loadHomeItems()
    }

    private fun loadHomeItems() {
        ComponentFactory.appComponent.repository()
                .getAllHomeItems()
                .compose(Utils.applySingleSchedulers())
                .subscribe { t1, t2 ->
                    t1?.let {
                        liveItems.value = t1
                    }
                    t2?.let {
                        Log.e("Items","could not get home items", it)
                    }
                }
    }
}
