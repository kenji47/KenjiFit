package com.kenji1947.realmfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kenji1947.realmfit.model.example1.Dog;
import com.kenji1947.realmfit.model.example1.Person;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 08.05.2017.
 */

public class RealmTestActivity extends AppCompatActivity {
    Realm realm;
    Realm realm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        realm2 = Realm.getDefaultInstance();
        Timber.d("Equals: " + realm.equals(realm2)); //true
        Timber.d("realm ins == " + (realm == realm2)); //true
        //foo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Timber.d("Run");
                Realm realm3 = Realm.getDefaultInstance();
                Timber.d("Equals realm3, realm: " + realm.equals(realm3)); //false
                Timber.d("realm ins realm3 == realm " + (realm == realm3)); //false

                for (int i = 0; i < 30; i++) {
                    Timber.d("sleep: " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Timber.d("interrupt: " + i);
                    }
                }
            }
        }).start();
    }


    private void foo() {
        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);

        realm.beginTransaction();
        final Dog managedDog = realm.copyToRealm(dog);
        Person person = realm.createObject(Person.class, "1");
        person.getDogs().add(managedDog);
        realm.commitTransaction();

        Timber.d("person.getDogs().size: " + person.getDogs().size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        realm.close();
        realm2.close();
    }
}
