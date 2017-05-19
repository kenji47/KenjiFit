package com.kenji1947.kenjifit.screen_ex_detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bumptech.glide.Glide;
import com.kenji1947.kenjifit.App;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.repo.Repository;
import com.kenji1947.kenjifit.repo.RepositoryDefault;
import com.kenji1947.kenjifit.repo.db.ObjExercise;
import com.kenji1947.kenjifit.repo.local.LocalRepoDefault;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by kenji1947 on 28.04.2017.
 */

public class ExDetailActivity extends MvpAppCompatActivity {
    public final static String EXERCISE_ID = "ex_id";

    private ObjExercise data;

    private CompositeSubscription compositeSubscription;
    private Repository repository;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.subToolbarTextView)
    public TextView exNameTextView;

//    @BindView(R.id.stepImageViewPager)
//    public ViewPager viewPager;

//    @BindView(R.id.stepDescriptionTextView)
//    public TextView stepDescription;

    @BindView(R.id.exerciseDescriptionTextView)
    public TextView exDescription;

    @BindView(R.id.carouselView)
    public CarouselView carouselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_detail);
        Timber.d("Ex id: " + getIntent().getLongExtra(EXERCISE_ID, -1));
        ButterKnife.bind(this);
        initToolbar();
        //carouselView.setImageListener(imageListener);
        carouselView.setViewListener(viewListener);
        compositeSubscription = new CompositeSubscription();
        repository = RepositoryDefault.getInstance(
                LocalRepoDefault.getInstance(((App)getApplication()).getDaoSession()), null);


        loadEx(getIntent().getLongExtra(EXERCISE_ID, -1));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
    public void loadEx(long ex_id) {
        Timber.d("loadEx");
        compositeSubscription.clear();

        Subscription subscription = repository
                .getExerciseWithFull(ex_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Timber.d("On next id: " + result.getExercise().getId());
                            data = result;
                            initInfo(result);
                        },
                        throwable -> {
                            Timber.d("On error: " + throwable);
                        },
                        () -> {

                        }
                );
        compositeSubscription.add(subscription);
    }
    private void initInfo(ObjExercise exercise) {
        exNameTextView.setText(exercise.getName().getEN());
        exDescription.setText(exercise.getDescription().getEN());

        toolbar.setTitle(exercise.getExercise().getMUSCLE_GROUP_SERVER_ID());
        carouselView.setPageCount(exercise.getStepList().size());
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageDrawable(getResources().getDrawable(R.drawable.photo_menu));\
            Timber.d("setImageForPosition");
           if (data != null) {
               Glide
                       .with(ExDetailActivity.this)
                       .load(Uri.parse("file:///android_asset/images/"
                               + data.getStepList().get(position).getExerciseStep().getICON_URL()))
                       .centerCrop()
                       .crossFade()
                       .into(imageView);
               //stepDescription.setText(data.getStepList().get(position).getDescription().getEN());
           }

        }
    };
    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            Timber.d("setViewForPosition");
            View customView = getLayoutInflater().inflate(R.layout.item_carousel_ex_desc2, null);
            TextView labelTextView = (TextView) customView.findViewById(R.id.stepDescription);
            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.stepImage);

            if (data != null) {
                Glide
                        .with(ExDetailActivity.this)
                        .load(Uri.parse("file:///android_asset/images/"
                                + data.getStepList().get(position).getExerciseStep().getICON_URL()))
                        .crossFade()
                        .fitCenter()
                        .into(fruitImageView);
                labelTextView.setText(data.getStepList().get(position).getDescription().getEN());

            }
            carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
            return customView;
        }
    };
}
