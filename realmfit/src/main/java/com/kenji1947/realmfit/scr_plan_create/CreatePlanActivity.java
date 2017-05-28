package com.kenji1947.realmfit.scr_plan_create;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.kenji1947.realmfit.Injection;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.Day;
import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.repo.local.LocalRepository;
import com.kenji1947.realmfit.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by kenji1947 on 18.05.2017.
 */

public class CreatePlanActivity extends MvpAppCompatActivity {

    @BindView(R.id.savePlanButton) Button savePlanButton;
    @BindView(R.id.addDays) Button addDaysButton;

    @BindView(R.id.planGoalSpinner) Spinner planGoalSpinner;
    @BindView(R.id.trainingFrequencySpinner) Spinner trainingFrequencySpinner;
    @BindView(R.id.genderSpinner) Spinner genderSpinner;

    Realm realm;
    LocalRepository localRepository;
    CompositeSubscription compositeSubscription;
    RealmResults<PlanGoal> planGoals;

    Realm.Transaction.OnSuccess l = new Realm.Transaction.OnSuccess() {
        @Override
        public void onSuccess() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_create);

        Timber.d("onCreate");
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        localRepository = Injection.provideRepositoryDefault().getLocalRepository();
        compositeSubscription = new CompositeSubscription();

        //todo Подумать про локаль
        Locale localeDefault = Locale.getDefault();
        Timber.d("localeDefault: " + localeDefault + " getLanguage: " + localeDefault.getLanguage());

        //-----------------
        List<String> list = new ArrayList<String>();
        //todo Подумать над раскидыванием планов
        planGoals = realm.where(PlanGoal.class).notEqualTo("_id", "user").findAll();
        for (PlanGoal plangoal: planGoals) {
            list.add(plangoal.getName().getEn());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planGoalSpinner.setAdapter(spinnerAdapter);


        savePlanButton.setOnClickListener((v) -> savePlan());
        addDaysButton.setOnClickListener((v) -> addDays());


    }

    private void savePlan() {
        Timber.d("savePlan");
        Timber.d("getSelectedItem: " + planGoalSpinner.getSelectedItem()
                + " getSelectedItemPosition: " + planGoalSpinner.getSelectedItemPosition()
                + " plan goal id: " + planGoals.get(planGoalSpinner.getSelectedItemPosition()).get_id());

        validateForm();

//        EspressoIdlingResource.increment();
//        Subscription subscription = localRepository.createPlanObs(
//                UUID.randomUUID().toString(),
//                "Мой новый план",
//                "План для набора массы",
//                "",
//                true,
//                3,
//                "male",
//                "user")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        (id) -> {
//                            Timber.d("onNext id: " + id);
//                        },
//                        (throwable) -> {
//                            Timber.d("onError " + throwable);
//                        },
//                        () -> {
//                            Timber.d("onComplete");
//                            EspressoIdlingResource.decrement();
//                            onBackPressed();
//                        }
//                );
//        compositeSubscription.add(subscription);
    }

    private void validateForm() {
    }


    private void addDays() {
        Timber.d("addDaysButton");
//        realm.executeTransaction((t) -> {
//
//
//        });
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Plan plan = realm.where(Plan.class).equalTo("_id", "e24281bb-ae82-4072-9b35-8de87f073d9d").findFirst();

                Day day = realm.createObject(Day.class);
                day.setPos(4);
                Day day2 = realm.createObject(Day.class);
                day2.setPos(4);

                plan.getDays().add(day);
                plan.getDays().add(day2);

            }
        }, l, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Timber.d("onError");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Timber.d("onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        //todo
        compositeSubscription.clear();
        realm.close();
    }
}
