package com.kenji1947.realmfit.scr_ex_catalog;

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

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.ex_catalog.ExName;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.model.ex_catalog.MuscleGroup;
import com.kenji1947.realmfit.scr_ex_detail.ExDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ObjectChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObjectChangeListener;
import timber.log.Timber;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class ExListFragment extends Fragment {
    public final static String GROUP_ID = "group_id";

    @BindView(R.id.buttonInsert) Button insert;
    @BindView(R.id.buttonDelete) Button delete;

    private Realm realm;
    private RealmList<Exercise> exercises;
    private Exercise exercise;
    private Exercise exerciseFirst;

    private RealmChangeListener<RealmList<Exercise>> exListChangeListener;
    private OrderedRealmCollectionChangeListener<RealmList<Exercise>> exListOrderedListener;

    private RealmObjectChangeListener<RealmModel> exQueryGroupObjectChangeListener;
    private RealmChangeListener<RealmModel> exQueryGroupChangeListener;

    private RealmObjectChangeListener<RealmModel> exFirstObjectChangeListener;
    private RealmChangeListener<RealmModel> exFirstChangeListener;


    private RealmExListAdapter adapter;
    private RealmExListAdapter.ItemClickListener listClickListener = new RealmExListAdapter.ItemClickListener() {
        @Override
        public void onClick(String ex_id) {
            Timber.d("onClick server_id: " + ex_id);
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
        Timber.d("onCreate " + getArguments().getString(GROUP_ID));
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        ButterKnife.bind(this, view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        exercises = realm
                .where(MuscleGroup.class)
                .contains("serverId", getArguments().getString(GROUP_ID)).findFirst().getExercises();
        exercise = exercises.get(0);
        exerciseFirst = realm.where(Exercise.class).equalTo("serverId", "squats").findFirst();

        adapter = new RealmExListAdapter(exercises, listClickListener);

        recyclerView.setAdapter(adapter);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise ex = realm.where(Exercise.class).equalTo("serverId", "squats").findFirst();
                ExName name = new ExName();
                name.setEn("en NEW!!!");
                name.setRu("ru NEW!!!");

                Timber.d("ex.isValid: " + ex.isValid());
                realm.executeTransaction((realm) -> { ex.getName().setRu("Новое 2");});
            }
        });

        exListChangeListener = element -> Timber.d(
                "ExList RealmChangeListener group: " + getArguments().getString(GROUP_ID));
        exercises.addChangeListener(exListChangeListener);

        exListOrderedListener = (collection, changeSet) -> Timber.d(
                "ExList OrderedRealmCollectionChangeListener group: " + getArguments().getString(GROUP_ID));
        exercises.addChangeListener(exListOrderedListener);

        //--------------
        exQueryGroupObjectChangeListener = (object, changeSet) -> Timber.d(
                "Ex RealmObjectChangeListener group: " + getArguments().getString(GROUP_ID));
        exercise.addChangeListener(exQueryGroupObjectChangeListener);

        exQueryGroupObjectChangeListener = (object, changeSet) -> Timber.d("Ex RealmChangeListener  group: " + getArguments().getString(GROUP_ID));
        exercise.addChangeListener(exQueryGroupObjectChangeListener);
        //-----------------------

        exFirstObjectChangeListener = new RealmObjectChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel object, ObjectChangeSet changeSet) {
                Timber.d("exerciseFirst RealmObjectChangeListener group: " + getArguments().getString(GROUP_ID));
            }
        };
        exerciseFirst.addChangeListener(exFirstObjectChangeListener);

        exFirstChangeListener = element -> Timber.d("exerciseFirst RealmChangeListener  group: " + getArguments().getString(GROUP_ID));
        exerciseFirst.addChangeListener(exFirstChangeListener);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
            Timber.d("onDestroy " + getArguments().getString(GROUP_ID));
            exercises.removeAllChangeListeners();
            exercise.removeAllChangeListeners();
            exerciseFirst.removeAllChangeListeners();
            realm.close();
        }
}
