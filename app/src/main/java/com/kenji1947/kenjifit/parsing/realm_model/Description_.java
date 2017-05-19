
package com.kenji1947.kenjifit.parsing.realm_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Description_ extends RealmObject {

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
