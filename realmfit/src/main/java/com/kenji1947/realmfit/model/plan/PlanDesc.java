package com.kenji1947.realmfit.model.plan;

import com.kenji1947.realmfit.model.LocaleSensitiveResource;

import io.realm.RealmObject;

/**
 * Created by kenji1947 on 12.05.2017.
 */

public class PlanDesc extends RealmObject implements LocaleSensitiveResource{
    private String en;
    private String ru;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }
}
