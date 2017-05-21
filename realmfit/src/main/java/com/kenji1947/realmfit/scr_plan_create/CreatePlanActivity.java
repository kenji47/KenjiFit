package com.kenji1947.realmfit.scr_plan_create;

import android.os.Bundle;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.kenji1947.realmfit.Injection;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.repo.local.LocalRepository;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 18.05.2017.
 */

public class CreatePlanActivity extends MvpAppCompatActivity {
    Realm realm;
    @BindView(R.id.savePlanButton) Button savePlanButton;
    @BindView(R.id.addDays) Button addDays;
    LocalRepository localRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_create);

        Timber.d("onCreate");
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        localRepository = Injection.provideRepositoryDefault().getLocalRepository();

        Locale localeDefault = Locale.getDefault();

        Timber.d("localeDefault: " + localeDefault + " getLanguage: " + localeDefault.getLanguage());

        savePlanButton.setOnClickListener((v) -> savePlan());
        addDays.setOnClickListener((v) -> addDays());
    }

    private void savePlan() {
        Timber.d("savePlan");
        localRepository.createPlanAsync(
                realm,
                UUID.randomUUID().toString(),
                "Мой новый план",
                "План для набора массы",
                "",
                true,
                3,
                "male",
                "mass"
        );
    }
    private void addDays() {
        Timber.d("addDays");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        realm.close();
    }
}
