package news.agoda.com.technewssample.listing;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import news.agoda.com.technewssample.R;
import news.agoda.com.technewssample.newslisting.NewsListingActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NewsListingActivityTest
{
    @Rule
    public final ActivityTestRule<NewsListingActivity> activityTestRule = new ActivityTestRule<>(NewsListingActivity.class);

    @Test
    public void shouldBeAbleToLaunchMainScreen()
    {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.action_news)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToLoadNews() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.news_listing)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToScrollViewNewsDetails() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.news_listing)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withText("Summary")).check(matches(isDisplayed()));
    }
}
