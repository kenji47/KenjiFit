
package com.kenji1947.kenjifit.parsing.dictionary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Exercise {

    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("performance")
    @Expose
    private Integer performance;
    @SerializedName("pmCoefficientsStr")
    @Expose
    private String pmCoefficientsStr;
    @SerializedName("weight")
    @Expose
    private Boolean weight;
    @SerializedName("weightForFemale")
    @Expose
    private Boolean weightForFemale;
    @SerializedName("serverId")
    @Expose
    private String serverId;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("typeName")
    @Expose
    private String typeName;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public String getPmCoefficientsStr() {
        return pmCoefficientsStr;
    }

    public void setPmCoefficientsStr(String pmCoefficientsStr) {
        this.pmCoefficientsStr = pmCoefficientsStr;
    }

    public Boolean getWeight() {
        return weight;
    }

    public void setWeight(Boolean weight) {
        this.weight = weight;
    }

    public Boolean getWeightForFemale() {
        return weightForFemale;
    }

    public void setWeightForFemale(Boolean weightForFemale) {
        this.weightForFemale = weightForFemale;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
