package app.krunal3kapadiya.facts

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import app.krunal3kapadiya.facts.ui.factsList.FactsListActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
@SmallTest
class FactsListActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<FactsListActivity> =
        ActivityTestRule(FactsListActivity::class.java)

    @Test
    fun checkRecyclerView() {
        TimeUnit.SECONDS.sleep(5)// wait for api call
        onView(withId(R.id.rv_fact)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewIsNotEmpty() {
        TimeUnit.SECONDS.sleep(5)// wait for api call
        onView(withId(R.id.rv_fact)).check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(
                view is RecyclerView &&
                        view.adapter != null && view.adapter?.itemCount ?: -1 > 0
            )
        }
    }
}