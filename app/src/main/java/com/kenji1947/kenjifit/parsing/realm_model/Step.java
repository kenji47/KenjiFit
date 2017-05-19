
package com.kenji1947.kenjifit.parsing.realm_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Step extends RealmObject {

    @SerializedName("description")
    @Expose
    private Description_ description;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;

    public Description_ getDescription() {
        return description;
    }

    public void setDescription(Description_ description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
