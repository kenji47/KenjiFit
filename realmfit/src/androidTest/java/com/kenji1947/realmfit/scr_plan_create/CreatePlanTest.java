package com.kenji1947.realmfit.scr_plan_create;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.scr_plan_catalog.PlanCatalogActivity3;
import com.kenji1947.realmfit.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by kenji1947 on 29.05.2017.
 */
@RunWith(AndroidJUnit4.class)
public class CreatePlanTest {
    @Rule
    public ActivityTestRule<CreatePlanActivity> rule =
            new ActivityTestRule<>(CreatePlanActivity.class, false, true);

    @Before
    public void setup() {
        Espresso.registerIdlingResources(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void after() {
        Espresso.unregisterIdlingResources(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void fillEdit() {
        onView(withId(R.id.planNameEditText)).perform(replaceText("Привет мир"));
    }
}
