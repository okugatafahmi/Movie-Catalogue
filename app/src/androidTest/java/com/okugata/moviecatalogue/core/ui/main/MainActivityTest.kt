package com.okugata.moviecatalogue.core.ui.main

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.ui.detail.DetailActivity
import com.okugata.moviecatalogue.core.utils.DeviceLocale
import com.okugata.moviecatalogue.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private fun getActivityInstance(): Activity? {
        var currentActivity: Activity? = null
        getInstrumentation().runOnMainSync {
            val resumedActivities =
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext()) {
                currentActivity = resumedActivities.iterator().next()
            }
        }
        return currentActivity
    }

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))

        (getActivityInstance() as DetailActivity).let { activity ->
            val cls = activity.javaClass
            val field = cls.getDeclaredField("movie").apply { isAccessible = true }
            field.get(activity)?.let { movie ->
                (movie as Movie).let {
                    onView(withId(R.id.text_date)).check(
                        matches(withText(DeviceLocale.convertDate(it.releaseDate)))
                    )
                    onView(withId(R.id.text_title)).check(matches(withText(it.title)))
                    onView(withId(R.id.text_overview)).check(matches(withText(it.overview)))
                }
            }
        }

    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))

        (getActivityInstance() as DetailActivity).let { activity ->
            val cls = activity.javaClass
            val field = cls.getDeclaredField("tvShow").apply { isAccessible = true }
            field.get(activity)?.let { tvShow ->
                (tvShow as TvShow).let {
                    onView(withId(R.id.text_date)).check(
                        matches(withText(DeviceLocale.convertDate(it.firstAirDate)))
                    )
                    onView(withId(R.id.text_title)).check(matches(withText(it.name)))
                    onView(withId(R.id.text_overview)).check(matches(withText(it.overview)))
                }
            }
        }
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>( 0, click())
        )

        var favorite: Movie? = null

        (getActivityInstance() as DetailActivity).let { activity ->
            val cls = activity.javaClass
            val field = cls.getDeclaredField("movie").apply { isAccessible = true }
            field.get(activity)?.let { movie ->
                (movie as Movie).let {
                    favorite = movie
                }
            }
        }

        assertNotNull(favorite)

        onView(withId(R.id.favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        favorite?.let {
            onView(withId(R.id.text_date)).check(
                matches(withText(DeviceLocale.convertDate(it.releaseDate)))
            )
            onView(withId(R.id.text_title)).check(matches(withText(it.title)))
            onView(withId(R.id.text_overview)).check(matches(withText(it.overview)))
        }
        onView(withId(R.id.favorite)).perform(click())
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        var favorite: TvShow? = null

        (getActivityInstance() as DetailActivity).let { activity ->
            val cls = activity.javaClass
            val field = cls.getDeclaredField("tvShow").apply { isAccessible = true }
            field.get(activity)?.let { tvShow ->
                (tvShow as TvShow).let {
                    favorite = tvShow
                }
            }
        }

        assertNotNull(favorite)

        onView(withId(R.id.favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        favorite?.let {
            onView(withId(R.id.text_date)).check(
                matches(withText(DeviceLocale.convertDate(it.firstAirDate)))
            )
            onView(withId(R.id.text_title)).check(matches(withText(it.name)))
            onView(withId(R.id.text_overview)).check(matches(withText(it.overview)))
        }
        onView(withId(R.id.favorite)).perform(click())
    }
}