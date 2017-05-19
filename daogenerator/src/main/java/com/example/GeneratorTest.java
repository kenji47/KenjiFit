package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by kenji1947 on 08.05.2017.
 */

public class GeneratorTest {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.kenji1947.kenjifit.dao_test");
        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java/");
        }catch (Exception e){
            e.printStackTrace();
        }
        initPlan(schema);
        initDay(schema);
        initI18N(schema);
        initWorkout(schema);
    }

    private static void initWorkout(Schema schema) {
        Entity workout = schema.addEntity("OrmWorkout");
        workout.addIdProperty();
        workout.addLongProperty("day_id");
        workout.addLongProperty("day_pos_date");
        workout.addIntProperty("sets");
    }

    private static void initI18N(Schema schema) {
        Entity i18n = schema.addEntity("OrmI18N");
        i18n.addIdProperty();
        i18n.addStringProperty("ru");
        i18n.addStringProperty("en");
        i18n.addStringProperty("user");
    }

    private static void initDay(Schema schema) {
        Entity day = schema.addEntity("OrmDay");
        day.addIdProperty();
        day.addLongProperty("plan_id");
        day.addLongProperty("name_i18n");
        day.addLongProperty("desc_i18n");
        day.addLongProperty("plan_pos_date");
    }

    private static void initPlan(Schema schema) {
        Entity plan = schema.addEntity("OrmPlan");
        plan.addIdProperty();
        plan.addLongProperty("name_i18n");
        plan.addLongProperty("description_i18n");
        plan.addLongProperty("list_pos_date");
    }


}
