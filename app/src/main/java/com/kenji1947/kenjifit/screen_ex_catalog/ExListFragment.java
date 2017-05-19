package com.kenji1947.kenjifit.screen_ex_catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kenji1947.kenjifit.App;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.repo.Repository;
import com.kenji1947.kenjifit.repo.RepositoryDefault;
import com.kenji1947.kenjifit.repo.local.LocalRepoDefault;
import com.kenji1947.kenjifit.screen_ex_detail.ExDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ExListFragment extends Fragment{
    public final static String GROUP_ID = "group_id";

    private ExListAdapter adapter;

    private CompositeSubscription compositeSubscription;

    private Repository repository;

    private ExListAdapter.ItemClickListener listClickListener = new ExListAdapter.ItemClickListener() {
        @Override
        public void onClick(long ex_id) {
            Timber.d("onClick id: " + ex_id);
            Intent intent = new Intent(getActivity(), ExDetailActivity.class);
            intent.putExtra(ExDetailActivity.EXERCISE_ID, ex_id);
            startActivity(intent);
        }
    };

    public static ExListFragment newInstance(String group) {
        Bundle args = new Bundle();
        args.putString(GROUP_ID, group);
        ExListFragment fragment = new ExListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
        repository = RepositoryDefault.getInstance(
                LocalRepoDefault.getInstance(((App)getActivity().getApplication()).getDaoSession()), null);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        ButterKnife.bind(view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExListAdapter(new ArrayList<>(), listClickListener);
        recyclerView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadEx(getArguments().getString(GROUP_ID));
    }
    public void loadEx(String group) {
        Timber.d("loadEx");
        compositeSubscription.clear();

        Subscription subscription = repository
                .getExerciseListByGroup(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Timber.d("On next size: " + result.size());
                            adapter.addExercises(result);
                        },
                        throwable -> {
                            Timber.d("On error: " + throwable);
                        },
                        () -> {

                        }
                );
        compositeSubscription.add(subscription);
    }
}
