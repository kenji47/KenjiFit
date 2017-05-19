
package com.kenji1947.kenjifit.parsing.dictionary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Dictionary{

    @SerializedName("exercises")
    @Expose
    private List<Exercise> exercises = null;

    @SerializedName("name")
    @Expose
    private Name_ name;

    @SerializedName("serverId")
    @Expose
    private String serverId;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
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
