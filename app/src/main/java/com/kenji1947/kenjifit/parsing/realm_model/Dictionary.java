
package com.kenji1947.kenjifit.parsing.realm_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Dictionary extends RealmObject{

    @SerializedName("exercises")
    @Expose
    private RealmList<Exercise> exercises = null;

    @SerializedName("name")
    @Expose
    private Name_ name;

    @SerializedName("serverId")
    @Expose
    private String serverId;

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Name_ getName() {
        return name;
    }

    public void setName(Name_ name) {
        this.name = name;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

}
