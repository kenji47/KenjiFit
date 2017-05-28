package com.kenji1947.realmfit.test_util;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.kenji1947.realmfit.util.AdapterGetData;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MyViewAssertions {
    //Checks adapter inner data
    public static class RecyclerViewDataAssertion<T> implements ViewAssertion {
        private final T data;

        public RecyclerViewDataAssertion(T data) {
            this.data = data;
        }
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            RecyclerView recyclerView = (RecyclerView) view;
            AdapterGetData<T> adapter = (AdapterGetData<T>)recyclerView.getAdapter();
            T data_from_adapter = adapter.getData();
            ViewMatchers.assertThat(data_from_adapter, equalTo(data));
        }
    }
    //Checks adapter list size
    public static class RecyclerViewCountItemsAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewCountItemsAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            ViewMatchers.assertThat(adapter.getItemCount(), CoreMatchers.is(expectedCount));
        }
    }

    //Checks SwipeRefreshLayout progress bar state
    public static class SwipeRefreshLayoutProgressAssertion implements ViewAssertion {
        private boolean active;

        public SwipeRefreshLayoutProgressAssertion(boolean active) {
            this.active = active;
        }
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
            assertThat(active, is(swipeRefreshLayout.isRefreshing()));
        }
    }
    public static class RecyclerItemSelectedAssertion implements ViewAssertion {
        private int position;

        public RecyclerItemSelectedAssertion(int position) {
            this.position = position;
        }
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.ViewHolder viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position);
            assertThat(true, is(viewHolder.itemView.isSelected()));
        }
    }

}
