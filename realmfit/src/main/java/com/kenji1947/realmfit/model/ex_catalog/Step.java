
package com.kenji1947.realmfit.model.ex_catalog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Step extends RealmObject {
    @SerializedName("description")
    @Expose
    private StepDescription description;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;

    public StepDescription getDescription() {
        return description;
    }

    public void setDescription(StepDescription description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
