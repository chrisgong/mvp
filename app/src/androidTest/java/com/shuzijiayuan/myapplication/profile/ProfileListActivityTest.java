package com.shuzijiayuan.myapplication.profile;

import android.support.test.rule.ActivityTestRule;

import com.shuzijiayuan.myapplication.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by gc on 2016/11/10.
 */
public class ProfileListActivityTest {

    @Rule
    public ActivityTestRule<ProfileListActivity> rule = new ActivityTestRule<>(ProfileListActivity.class);

    @Test
    public void testLogin() {
        onView(withId(R.id.pb_loading)).check(matches(isDisplayed()));
        Observable.just(R.id.list_profile)
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer id) {
                        onData(anything())
                                .inAdapterView(withId(id))
                                .atPosition(0)
                                .onChildView(withId(R.id.tv_name))
                                .check(matches(withText(containsString("chris"))));
                    }
                });
    }
}