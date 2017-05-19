
package com.kenji1947.kenjifit.parsing.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name_ {

    @SerializedName("en_male")
    @Expose
    private String enMale;
    @SerializedName("ru_male")
    @Expose
    private String ruMale;
    @SerializedName("en_female")
    @Expose
    private String enFemale;
    @SerializedName("ru_female")
    @Expose
    private String ruFemale;

    public String getEnMale() {
        return enMale;
    }

    public void setEnMale(String enMale) {
        this.enMale = enMale;
    }

    public String getRuMale() {
        return ruMale;
    }

    public void setRuMale(String ruMale) {
        this.ruMale = ruMale;
    }

    public String getEnFemale() {
        return enFemale;
    }

    public void setEnFemale(String enFemale) {
        this.enFemale = enFemale;
    }

    public String getRuFemale() {
        return ruFemale;
    }

    public void setRuFemale(String ruFemale) {
        this.ruFemale = ruFemale;
    }

}
