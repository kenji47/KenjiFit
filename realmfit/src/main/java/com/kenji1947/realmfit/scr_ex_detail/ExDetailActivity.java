package com.kenji1947.realmfit.scr_ex_detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class ExDetailActivity extends AppCompatActivity {
    public final static String EXERCISE_ID = "ex_id";

    Realm realm;
    Exercise exercise;

    @BindView(R.id.toolbar) public Toolbar toolbar;
    @BindView(R.id.subToolbarTextView) public TextView exNameTextView;
    @BindView(R.id.exerciseDescriptionTextView) public TextView exDescription;
    @BindView(R.id.carouselView) public CarouselView carouselView;

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            Timber.d("setViewForPosition");
            View customView = getLayoutInflater().inflate(R.layout.item_carousel_ex_desc2, null);
            TextView labelTextView = (TextView) customView.findViewById(R.id.stepDescription);
            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.stepImage);

            if (exercise != null) {
                Glide
                        .with(ExDetailActivity.this)
                        .load(Uri.parse("file:///android_asset/images/"
                                + exercise.getSteps().get(position).getIconUrl()))
                        .crossFade()
                        .fitCenter()
                        .into(fruitImageView);
                labelTextView.setText(exercise.getSteps().get(position).getDescription().getEn());

            }
            carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
            return customView;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_detail);

        Timber.d("Ex id: " + getIntent().getStringExtra(EXERCISE_ID));

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        exercise = realm.where(Exercise.class)
                .equalTo("serverId", getIntent().getStringExtra(EXERCISE_ID)).findFirst();

        carouselView.setViewListener(viewListener);

        //initToolbar();
        initInfo();
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

    }

    private void initInfo() {
        exNameTextView.setText(exercise.getName().getEn());
        exDescription.setText(exercise.getDescription().getEn());

        //toolbar.setTitle(exercise.getServerId());
        carouselView.setPageCount(exercise.getSteps().size());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
