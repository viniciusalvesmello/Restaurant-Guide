package io.github.viniciusalvesmello.restaurantguide

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun mainActivityTest() {


        Thread.sleep(1000)
        val openRestaurantsList = onView(
            allOf(
                withId(R.id.card_view_city),
                childAtPosition(
                    allOf(
                        withId(R.id.recycle_view_cities),
                        childAtPosition(
                            withId(R.id.contraint_layout_detail),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        openRestaurantsList.perform(click())


        Thread.sleep(1000)
        val openRestaurantDetails = onView(
            allOf(
                withId(R.id.card_view_restaurant),
                childAtPosition(
                    allOf(
                        withId(R.id.recycle_view_restaurants),
                        childAtPosition(
                            withId(R.id.contraint_layout_detail),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        openRestaurantDetails.perform(click())


        Thread.sleep(1000)
        val openRestaurantMaps = onView(
            allOf(
                withId(R.id.image_view_googlemaps),
                childAtPosition(
                    allOf(
                        withId(R.id.contraint_layout_top),
                        childAtPosition(
                            withId(R.id.contraint_layout_main),
                            1
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        openRestaurantMaps.perform(click())


        Thread.sleep(1000)
        val returnToRestaurantDetails = onView(
            allOf(
                withId(R.id.constraint_layout_back_pressed),
                childAtPosition(
                    allOf(
                        withId(R.id.contraint_layout_action_bar),
                        childAtPosition(
                            withId(R.id.contraint_layout_main),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        returnToRestaurantDetails.perform(click())


        Thread.sleep(1000)
        val returnToRestaurantsList = onView(
            allOf(
                withId(R.id.constraint_layout_back_pressed),
                childAtPosition(
                    allOf(
                        withId(R.id.contraint_layout_action_bar),
                        childAtPosition(
                            withId(R.id.contraint_layout_main),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        returnToRestaurantsList.perform(click())

        Thread.sleep(1000)
        val returnToCitiesList = onView(
            allOf(
                withId(R.id.constraint_layout_back_pressed),
                childAtPosition(
                    allOf(
                        withId(R.id.contraint_layout_action_bar),
                        childAtPosition(
                            withId(R.id.contraint_layout_main),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        returnToCitiesList.perform(click())

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}