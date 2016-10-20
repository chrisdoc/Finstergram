package xyz.husten.finstergram.ui.finstergrams;

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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasSize;

@LargeTest @RunWith(AndroidJUnit4.class) public class FinsterGridActivityTest {

  @Rule public ActivityTestRule<FinsterGridActivity> mActivityTestRule =
      new ActivityTestRule<>(FinsterGridActivity.class);

  @Test public void finsterGridActivityTest() {
    ViewInteraction imageView = onView(allOf(withId(R.id.iv_image),
        childAtPosition(childAtPosition(withId(R.id.rv_results), 0), 0), isDisplayed()));
    imageView.check(matches(isDisplayed()));

    ViewInteraction textView = onView(allOf(withId(R.id.tv_like_count), withText("❤️ 21"),
        childAtPosition(childAtPosition(withId(R.id.rv_results), 0), 1), isDisplayed()));
    textView.check(matches(withText("❤️ 21")));

  }

  public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
    return new RecyclerViewMatcher(recyclerViewId);
  }

  private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
      final int position) {

    return new TypeSafeMatcher<View>() {
      @Override public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
            ((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
