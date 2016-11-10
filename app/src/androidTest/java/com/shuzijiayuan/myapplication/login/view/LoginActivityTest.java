package com.shuzijiayuan.myapplication.login.view;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import com.shuzijiayuan.myapplication.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
/**
 * Created by gc on 2016/11/9.
 */
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testLogin(){
        onView(withId(R.id.phone)).perform(typeText("18618416321"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("880316"), closeSoftKeyboard());
        onView(withId(R.id.phone_sign_in_button)).perform(click());

        onView(withText(R.string.result_login_success)).inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}