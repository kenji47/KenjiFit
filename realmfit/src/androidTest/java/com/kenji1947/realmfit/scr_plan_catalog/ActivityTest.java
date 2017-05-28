package com.kenji1947.realmfit.scr_plan_catalog;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.test_util.MyMatcher;
import com.kenji1947.realmfit.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by kenji1947 on 24.05.2017.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityTest {
    @Rule
    public ActivityTestRule<PlanCatalogActivity3> rule =
            new ActivityTestRule<>(PlanCatalogActivity3.class, false, true);

    @Before
    public void setup() {
        Espresso.registerIdlingResources(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void after() {
        Espresso.unregisterIdlingResources(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void firstLaunch() {
        onView(withId(R.id.createPlan)).perform(click());
        onView(withId(R.id.savePlanButton)).perform(click());

        onView(withId(R.id.planListRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.planListRecyclerView))
                .perform(RecyclerViewActions
                        .actionOnHolderItem(MyMatcher.findHolderInPlanListByName("Мой новый план"), click()));


//        onView(withId(R.id.planListRecyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.planListRecyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
//        onView(withId(R.id.planListRecyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
}
