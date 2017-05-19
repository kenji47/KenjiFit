package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.kenji1947.kenjifit.dao");
        initExercise(schema);
        initExerciseStep(schema);
        initI18N(schema);

        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java/");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void initTrainingPlan(Schema schema) {
        Entity plan = schema.addEntity("OrmPlan");
        plan.addIdProperty();
        plan.addLongProperty("name_id");
        plan.addLongProperty("description_id");
        plan.addIntProperty("gender");
        plan.addIntProperty("frequency");
        plan.addLongProperty("goal_id");
        plan.addIntProperty("plan_position");
    }

    private static void initTrainingDays(Schema schema) {
        Entity day = schema.addEntity("OrmTrainingDays");
        day.addIdProperty();
        day.addLongProperty("plan_id");
        day.addLongProperty("name_id");
        day.addIntProperty("plan_position");
    }

    private static void initSet(Schema schema) {
        Entity day = schema.addEntity("OrmTrainingDays");
        day.addIdProperty();

    }


    private static void initExercise(Schema schema) {
        Entity exercise = schema.addEntity("OrmExercise");
        exercise.addIdProperty();
        exercise.addStringProperty("SERVER_ID");
        exercise.addStringProperty("ICON_URL");
        exercise.addIntProperty("PERFORMANCE");
        exercise.addStringProperty("TYPE_NAME");
        exercise.addIntProperty("WEIGHT");
        exercise.addIntProperty("WEIGHT_FOR_FEMALE");
        exercise.addIntProperty("PM_COEFFICIENTS_STR");
        exercise.addStringProperty("MUSCLE_GROUP_SERVER_ID");
        exercise.addIntProperty("NAME_ID");
        exercise.addIntProperty("DESCRIPTION_ID");
    }
    private static void initExerciseStep(Schema schema) {
        Entity exercise = schema.addEntity("OrmExerciseStep");
        exercise.addIdProperty();
        exercise.addStringProperty("ICON_URL");
        exercise.addIntProperty("DESCRIPTION_ID");
        exercise.addIntProperty("EXERCISE_ID");
    }

    private static void initI18N(Schema schema) {
        Entity exercise = schema.addEntity("OrmI18N");
        exercise.addIdProperty();
        exercise.addStringProperty("RU");
        exercise.addStringProperty("EN");
    }
}
