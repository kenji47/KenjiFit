
package com.kenji1947.realmfit.model.ex_catalog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Exercise extends RealmObject {
    @PrimaryKey
    @SerializedName("serverId")
    @Expose
    private String serverId;

    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("name")
    @Expose
    private ExName name;
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


    @SerializedName("description")
    @Expose
    private ExDescription description;
    @SerializedName("steps")
    @Expose
    private RealmList<Step> steps = null;
    @SerializedName("typeName")
    @Expose
    private String typeName;

    private MuscleGroup muscleGroup;

    private boolean isUser;

    public boolean isUser() {
        return isUser;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ExName getName() {
        return name;
    }

    public void setName(ExName name) {
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

    public ExDescription getDescription() {
        return description;
    }

    public void setDescription(ExDescription description) {
        this.description = description;
    }

    public RealmList<Step> getSteps() {
        return steps;
    }

    public void setSteps(RealmList<Step> steps) {
        this.steps = steps;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
