
package com.kenji1947.realmfit.model.ex_catalog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MuscleGroup extends RealmObject{
    @PrimaryKey
    @SerializedName("serverId")
    @Expose
    private String serverId;

    @SerializedName("exercises")
    @Expose
    private RealmList<Exercise> exercises = null;

    @SerializedName("name")
    @Expose
    private MuscleGroupName name;

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public MuscleGroupName getName() {
        return name;
    }

    public void setName(MuscleGroupName name) {
        this.name = name;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

}
