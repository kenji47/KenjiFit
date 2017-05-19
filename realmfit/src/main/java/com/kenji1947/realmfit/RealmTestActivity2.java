package com.kenji1947.realmfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kenji1947.realmfit.model.example1.Cat;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 10.05.2017.
 */

public class RealmTestActivity2 extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();


    }

    private void copyToRealmChangeObj_WillChangeSource() {
        final Cat cat = new Cat();
        cat.setName("william");
        cat.setAge(1);

        realm.beginTransaction();
        Cat copy_cat = realm.copyToRealm(cat);
        realm.commitTransaction();

        realm.beginTransaction();
        copy_cat.setAge(5);//Изменит данные
        realm.commitTransaction();
    }
    private void copyToRealmChangeSource_WillChangeObj() {
        final Cat cat = new Cat();
        cat.setName("dali");
        cat.setAge(1);

        realm.beginTransaction();
        Cat copy_cat = realm.copyToRealm(cat);
        realm.commitTransaction();

        Cat catDali = realm.where(Cat.class).contains("name", "dali").findFirst();
        realm.beginTransaction();
        catDali.setAge(10);
        realm.commitTransaction();

        Timber.d("copy_cat: " + copy_cat);//10
    }

    private void copyToRealmChangeSource_NotChangeObj() {
        final Cat cat = new Cat();
        cat.setName("mark");
        cat.setAge(1);

        realm.beginTransaction();
        Cat copy_cat = realm.copyToRealm(cat);
        realm.commitTransaction();

        realm.beginTransaction();
        cat.setAge(10);
        realm.commitTransaction();

        Timber.d("copy_cat: " + copy_cat);// copy_cat: 1; source 1
    }

    private void whereUpdateSource_WillUpdateObj() {
        //Здесь нет магии обновления, каждый раз возвр один и тот же объект и я сам его обновляю
        Cat catAlex = realm.where(Cat.class).contains("name", "alex").findFirst();

        Cat catAlex2 = realm.where(Cat.class).contains("name", "alex").findFirst();

        realm.beginTransaction();
        catAlex2.setAge(3);
        realm.commitTransaction();
        Timber.d("catAlex" + catAlex); //catAlex.old == 3

        Timber.d("catAlex.equals(catAlex2): " + catAlex.equals(catAlex2)); //true
    }

    private void whereChangeObj_WillChangeSource() {
        Cat catAlex = realm.where(Cat.class).contains("name", "alex").findFirst();
        Timber.d("catAlex: " + catAlex);

        realm.beginTransaction();
        catAlex.setAge(2); //Изменит данные
        realm.commitTransaction();
    }

    private void insertCats() {
        final Cat alexCat = new Cat();
        alexCat.setName("alex");
        alexCat.setAge(1);

        final Cat charlieCat = new Cat();
        charlieCat.setName("charlie");
        charlieCat.setAge(1);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(alexCat);
                realm.insert(charlieCat);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
