package id.itborneo.beritakini.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.itborneo.beritakini.R
import id.itborneo.beritakini.main.MainActivity
import id.itborneo.core.utils.test.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeritaKiniAndroidTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource())
    }

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun loadNews() {

        val recycler = withId(R.id.rvNews)

        Espresso.onView(recycler).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(recycler).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun loadDetailNews() {


        val position = 1
        val recycler = withId(R.id.rvNews)


        Espresso.onView(recycler).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvAuthor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvSourceName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testFavorite() {


        val position = 1
        val recycler = withId(R.id.rvNews)


        Espresso.onView(recycler).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                ViewActions.click()
            )
        )


        Espresso.onView(withId(R.id.ivBookmark)).perform(ViewActions.click())

        Espresso.pressBack()

        Espresso.onView(withId(R.id.bookmarksFragment)).perform(ViewActions.click())






    }

}