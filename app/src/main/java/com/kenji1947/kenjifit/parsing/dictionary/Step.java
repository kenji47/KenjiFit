
package com.kenji1947.kenjifit.parsing.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {

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
