package com.kenji1947.realmfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kenji1947.realmfit.model.example1.Cat;

import io.realm.ObjectChangeSet;
import io.realm.Realm;
import io.realm.RealmObjectChangeListener;
import timber.log.Timber;

/**
 * Created by kenji1947 on 10.05.2017.
 */

public class RealmListenersActivity extends AppCompatActivity {
    Realm realm;
    private final RealmObjectChangeListener<Cat> listener = new RealmObjectChangeListener<Cat>() {
        @Override
        public void onChange(Cat dog, ObjectChangeSet changeSet) {
            if (changeSet.isDeleted()) {
                Timber.d("The cat was deleted");
                return;
            }
            for (String fieldName : changeSet.getChangedFields()) {
                Timber.d("Field " + fieldName + " was changed.");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        Cat cat = realm.where(Cat.class).contains("name", "alex").findFirst();
        cat.addChangeListener(listener);

        Timber.d("start tr");
        realm.beginTransaction();
        cat.setAge(4);
        realm.commitTransaction();
        Timber.d("end tr");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Realm realm = Realm.getDefaultInstance();
                Cat cat = realm.where(Cat.class).contains("name", "alex").findFirst();
                realm.beginTransaction();
                cat.setAge(10);
                realm.commitTransaction();
                realm.close();
            }
        }).start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
        realm.close();
    }
}
