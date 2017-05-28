package com.kenji1947.realmfit.test_util;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.scr_plan_catalog.ChildHolder;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import timber.log.Timber;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by kenji1947 on 04.04.2017.
 */

public class MyMatcher {
    /**
     * Проверяет факт, что переданный Холдер находится по указанной позиции в адаптере.<p/>
     * Usage:
     * <pre>
     * onView(withId(R.id.city_search_list)).check(matches(atPosition(0, hasDescendant(withText("Moscow")))));
     * </pre>
     *
     * @param position    Holder position in adapter
     * @param itemMatcher Matcher, that contains Holder for check
     * @return
     */
    public static Matcher<View> checkHolderContainsAtPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder =
                        view.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> findHolderInPlanListByName(String name) {
        return new BoundedMatcher<RecyclerView.ViewHolder, ChildHolder>(
                ChildHolder.class
        ) {

            @Override
            public void describeTo(Description description) {
                description.appendText("ViewHolder found with text: " + name);
            }

            @Override
            protected boolean matchesSafely(ChildHolder item) {
                TextView nameTextView = (TextView) item.itemView.findViewById(R.id.planName);
                if (nameTextView == null) {
                    return false;
                }
                return nameTextView.getText().toString().contains(name);
            }
        };
    }

//    public static Matcher<RecyclerView.ViewHolder> findHolderCitiesAdapter(String city_name) {
//        return new BoundedMatcher<RecyclerView.ViewHolder, CitiesRecyclerViewAdapter.ViewHolder>(
//                CitiesRecyclerViewAdapter.ViewHolder.class
//        ) {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("ViewHolder found with text: " + city_name);
//            }
//
//            @Override
//            protected boolean matchesSafely(CitiesRecyclerViewAdapter.ViewHolder item) {
//                Timber.d(
//                        "MATCHER ViewHolder.getAdapterPosition:" + item.getAdapterPosition()
//                                + " ViewHolder.getLayoutPosition:" + item.getLayoutPosition()
//                );
//
//                TextView title_view = (TextView) item.view.findViewById(R.id.city_name);
//                if (title_view == null) {
//                    return false;
//                }
//                return title_view.getText().toString().contains(city_name);
//            }
//        };
//    }


    public static Matcher<RecyclerView> checkIfHolderSelectedCitiesAdapter(final int position, boolean isSelected) {
        return new BoundedMatcher<RecyclerView, RecyclerView>(RecyclerView.class) {
            @Override
            protected boolean matchesSafely(RecyclerView view) {
                RecyclerView.ViewHolder viewHolder =
                        view.findViewHolderForAdapterPosition(position);

                return viewHolder != null && viewHolder.itemView.isSelected();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has selected item at position " + position + ": ");

            }
        };
    }


}
