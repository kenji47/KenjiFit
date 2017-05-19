
package com.kenji1947.realmfit.model.ex_catalog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ExDescription extends RealmObject {
    @SerializedName("en")
    @Expose
    private String en;
    @SerializedName("ru")
    @Expose
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
