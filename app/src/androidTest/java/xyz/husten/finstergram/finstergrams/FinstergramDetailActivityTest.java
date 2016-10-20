package xyz.husten.finstergram.finstergrams;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.husten.finstergram.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class FinstergramDetailActivityTest {

  @Rule public ActivityTestRule<FinsterGridActivity> mActivityTestRule =
      new ActivityTestRule<>(FinsterGridActivity.class);

  @Test public void finstergramDetailActivityTest() {
    onView(withId(R.id.rv_results)).perform(actionOnItemAtPosition(0, click()));

    onView(withId(R.id.likes)).check(matches(withText("❤️ 21")));
    onView(withId(R.id.comments)).check(matches(withText("\uD83D\uDCAC 0")));
    onView(withId(R.id.username)).check(matches(withText("kieslichc")));

  }
}
