package com.kenji1947.realmfit.model.example1;

import io.realm.RealmObject;
import timber.log.Timber;

/**
 * Created by kenji1947 on 08.05.2017.
 */

public class Cat extends RealmObject {

    private String name;
    private int age;

    public String getName() {
        Timber.d("getName");
        return name;
    }

    public void setName(String name) {
        Timber.d("setName");
        this.name = name;
    }

    public int getAge() {
        Timber.d("getAge");
        return age;
    }

    public void setAge(int age) {
        Timber.d("setAge");
        this.age = age;
    }
}
