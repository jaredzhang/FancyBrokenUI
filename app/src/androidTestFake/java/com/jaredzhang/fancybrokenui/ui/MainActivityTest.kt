package com.jaredzhang.fancybrokenui.app
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.jaredzhang.fancybrokenui.R
import com.jaredzhang.fancybrokenui.ui.MainActivity
import com.jaredzhang.fancybrokenui.ui.RecyclerViewMatcher
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Test
    fun testItemsDisplay() {
        rule.launchActivity(Intent())

        onView(withId(R.id.rvHome)).check(recyclerViewItemCountAssertion(3))

        onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvHome)
                .atPositionOnView(0, R.id.tvTitle))
                .check(matches(withText("About Us")))
    }

}

fun recyclerViewItemCountAssertion(expectedCount: Int): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter.itemCount, `is`(expectedCount))
    }
}
